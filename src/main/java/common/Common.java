package common;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Common {
	@Nullable
	public static String getGitRootPath(@NotNull AnActionEvent e) {
		String path;
		try {
			Project project = e.getProject();
			Editor editor = e.getData(CommonDataKeys.EDITOR);
			Document document = FileEditorManager.getInstance(project).getSelectedTextEditor().getDocument();
			PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(document);
			Process process = Runtime.getRuntime().exec("git rev-parse --show-toplevel", null,
					new File(file.getVirtualFile().getParent().getPath()));
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			path = br.readLine();
			if(path == null || path.startsWith("fatal")) {
				Messages.showInfoMessage(path == null ? "Something wrong." : path, "Error");
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			Messages.showInfoMessage("Something wrong:" + ex.getMessage(), "Error");
			return null;
		}
		return path;
	}

	@Nullable
	public static String getCurrentFilePath(@NotNull AnActionEvent e) {
		try {
			Project project = e.getProject();
			Document currentDoc = FileEditorManager.getInstance(project).getSelectedTextEditor().getDocument();
			VirtualFile currentFile = FileDocumentManager.getInstance().getFile(currentDoc);
			return currentFile.getPath();
		} catch (Exception ex) {
			ex.printStackTrace();
			Messages.showInfoMessage("Something wrong:" + ex.getMessage(), "Error");
			return null;
		}
	}
}

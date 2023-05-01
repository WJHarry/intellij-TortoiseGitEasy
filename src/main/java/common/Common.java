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
import java.nio.file.Paths;

public class Common {
	@Nullable
	public static String getGitRootPath(@NotNull AnActionEvent e) {
		String path;
		try {
			Project project = e.getProject();
			if (project == null) {
				Messages.showInfoMessage("Please just open a project.", "Error");
				return null;
			}

			Editor selectedTextEditor = FileEditorManager.getInstance(project).getSelectedTextEditor();
			if (selectedTextEditor == null) {
				Messages.showInfoMessage("Please just open an editor.", "Error");
				return null;
			}

			Document document = selectedTextEditor.getDocument();
			PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(document);
			if (file == null) {
				Messages.showInfoMessage("Please just open a file.", "Error");
				return null;
			}
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
		// Ensure that it conforms to the Windows path style.
		return Paths.get(path).toString();
	}

	@Nullable
	public static String getCurrentFilePath(@NotNull AnActionEvent e) {
		try {
			Project project = e.getProject();
			if (project == null) {
				Messages.showInfoMessage("Please just open a project.", "Error");
				return null;
			}
			Editor selectedTextEditor = FileEditorManager.getInstance(project).getSelectedTextEditor();
			if (selectedTextEditor == null) {
				Messages.showInfoMessage("Please just open an editor.", "Error");
				return null;
			}
			Document currentDoc = selectedTextEditor.getDocument();
			VirtualFile currentFile = FileDocumentManager.getInstance().getFile(currentDoc);
			if (currentFile == null) {
				Messages.showInfoMessage("Please just open a file.", "Error");
				return null;
			}
			return currentFile.getPath();
		} catch (Exception ex) {
			ex.printStackTrace();
			Messages.showInfoMessage("Something wrong:" + ex.getMessage(), "Error");
			return null;
		}
	}
}

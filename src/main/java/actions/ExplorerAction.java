package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import common.Common;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ExplorerAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("explorer.exe " + Common.getGitRootPath(e));
		} catch (IOException ex) {
			ex.printStackTrace();
			Messages.showInfoMessage("Something wrong:" + ex.getMessage(), "Error");
		}
	}
}

package processes;

import com.intellij.openapi.actionSystem.AnActionEvent;
import common.Common;
import org.jetbrains.annotations.NotNull;

public class ActionProcess {
	public enum Location {
		ROOT, CURRENT_FILE
	}
	public static void actionPerformed(@NotNull AnActionEvent e, String command, Location location) {

		String path;
		if (location == Location.ROOT) {
			path = Common.getGitRootPath(e);
		} else {
			path = Common.getCurrentFilePath(e);
		}
		if (path == null) return;

		ExecuteProcess.execute(command, path);
	}
}

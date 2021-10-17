package processes;

import com.intellij.openapi.ui.Messages;
import settings.AppSettingsState;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecuteProcess {
	public static void execute(String command, String path) {
		Runtime runtime = Runtime.getRuntime();
		try {
			Path execPath;
			String homePath = AppSettingsState.getInstance().tortoiseGitHomePath;
			if (homePath == null || homePath.isBlank()) {
				execPath = Paths.get("TortoiseGitProc.exe");
			} else {
				execPath = Paths.get(homePath, "bin", "TortoiseGitProc.exe");
			}
			runtime.exec(execPath + " /command:" + command + " /path:" + path);
		} catch (IOException ex) {
			ex.printStackTrace();
			Messages.showInfoMessage("Something wrong:" + ex.getMessage(), "Error");
		}
	}
}

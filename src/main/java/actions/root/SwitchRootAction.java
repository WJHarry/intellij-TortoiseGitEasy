package actions.root;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import common.Common;
import org.jetbrains.annotations.NotNull;
import processes.ActionProcess;
import processes.ExecuteProcess;

public class SwitchRootAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		ActionProcess.actionPerformed(e, "switch", ActionProcess.Location.ROOT);
	}
}

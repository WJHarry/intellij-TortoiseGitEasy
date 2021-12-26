package actions.root.stash;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import common.Common;
import org.jetbrains.annotations.NotNull;
import processes.ActionProcess;
import processes.ExecuteProcess;

public class StashListRootAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		ActionProcess.actionPerformed(e, "reflog /ref:\"refs/stash\"", ActionProcess.Location.ROOT);
	}
}

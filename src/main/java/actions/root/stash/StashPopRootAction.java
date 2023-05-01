package actions.root.stash;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import processes.ActionProcess;

public class StashPopRootAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		ActionProcess.actionPerformed(e, "stashpop", ActionProcess.Location.ROOT);
	}
}

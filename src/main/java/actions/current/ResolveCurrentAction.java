package actions.current;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import processes.ActionProcess;

public class ResolveCurrentAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ActionProcess.actionPerformed(e, "resolve", ActionProcess.Location.CURRENT_FILE);
    }
}
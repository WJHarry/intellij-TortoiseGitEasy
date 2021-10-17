package settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

	private final JPanel myMainPanel;
	private final JBTextField tortoiseGitHomePath = new JBTextField();

	public AppSettingsComponent() {
		myMainPanel = FormBuilder.createFormBuilder()
				.addLabeledComponent(
						new JBLabel("TortoiseGit home path (For example:\"C:\\Program Files\\TortoiseGit\"): "),
						tortoiseGitHomePath, 1, true)
				.addComponentFillVertically(new JPanel(), 0)
				.getPanel();
	}

	public JPanel getPanel() {
		return myMainPanel;
	}

	public JComponent getPreferredFocusedComponent() {
		return tortoiseGitHomePath;
	}

	@NotNull
	public String getTortoiseGitHomePath() {
		return tortoiseGitHomePath.getText();
	}

	public void setTortoiseGitHomePath(@NotNull String newText) {
		tortoiseGitHomePath.setText(newText);
	}

}
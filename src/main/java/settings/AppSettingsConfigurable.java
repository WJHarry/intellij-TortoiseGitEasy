package settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class AppSettingsConfigurable implements Configurable {

	private AppSettingsComponent mySettingsComponent;

	// A default constructor with no arguments is required because this implementation
	// is registered as an applicationConfigurable EP

	@Nls(capitalization = Nls.Capitalization.Title)
	@Override
	public String getDisplayName() {
		return "TortoiseGitEasy";
	}

	@Override
	public JComponent getPreferredFocusedComponent() {
		return mySettingsComponent.getPreferredFocusedComponent();
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		mySettingsComponent = new AppSettingsComponent();
		return mySettingsComponent.getPanel();
	}

	@Override
	public boolean isModified() {
		AppSettingsState settings = AppSettingsState.getInstance();
		return !mySettingsComponent.getTortoiseGitHomePath().equals(settings.tortoiseGitHomePath);
	}

	@Override
	public void apply() {
		AppSettingsState settings = AppSettingsState.getInstance();
		settings.tortoiseGitHomePath = mySettingsComponent.getTortoiseGitHomePath();
	}

	@Override
	public void reset() {
		AppSettingsState settings = AppSettingsState.getInstance();
		mySettingsComponent.setTortoiseGitHomePath(settings.tortoiseGitHomePath);
	}

	@Override
	public void disposeUIResources() {
		mySettingsComponent = null;
	}

}
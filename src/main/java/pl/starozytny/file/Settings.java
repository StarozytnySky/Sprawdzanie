package pl.starozytny.file;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

	@Override
	protected boolean saveComments() {
		return true;
	}

	@Override
	protected int getConfigVersion() {
		return 1;
	}
}

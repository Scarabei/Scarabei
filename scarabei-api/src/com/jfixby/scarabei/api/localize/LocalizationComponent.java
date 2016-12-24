
package com.jfixby.scarabei.api.localize;

public interface LocalizationComponent {

	// LocalizationSpecs newLocalizationSpecs();
	//
	// Locale newLocalization(LocalizationSpecs loc_specs);
	//
	// void writeToFile(AbsolutePath<FileSystemMountPoint> cfg_file_path,
	// Locale locale) throws IOException;
	//
	// Locale readFromFile(AbsolutePath<FileSystemMountPoint> cfg_file_path)
	// throws IOException;

	StringValuesContainer getStringValuesContainer ();

	Locale getLocale (String locale_name);

}

package com.jfixby.red.image.argb;

public interface Snipp {

    void setZipCompressExtractedAlphaChannels(boolean zipCompressExtractedAlphaChannels);

    boolean zipCompressExtractedAlphaChannels();

    void setExtractAlphaChannes(boolean extractAlphaChannes);

    boolean extractAlphaChannes();

    // static {
    // boolean useZip = settings.zipCompressExtractedAlphaChannels();
    // boolean extractAlphaChannes = settings.extractAlphaChannes();
    // AlphaChannelExtractor alphaExtractor = null;
    // if (extractAlphaChannes) {
    // AlphaChannelExtractorSpecs alphaExtractorSpecs =
    // ETC1Compressor.newAlphaChannelExtractorSpecs();
    // alphaExtractorSpecs.setUseZIPCompression(useZip);
    // alphaExtractor =
    // ETC1Compressor.newAlphaChannelExtractor(alphaExtractorSpecs);
    // }
    // if (extractAlphaChannes) {
    //
    // AlphaChannelExtractionSettings alpphaExtractionParams =
    // alphaExtractor.newExtractionSettings();
    // alpphaExtractionParams.setInputFile(pageFile);
    // alpphaExtractionParams.setNameTag(newPageFileName);
    // alphaExtractor.process(alpphaExtractionParams);
    // }
    // if (extractAlphaChannes) {
    // byte[] bytes = alphaExtractor.serialize();
    // String alpha_channes_file_name = atlasFile.nameWithoutExtension()
    // + ETC1Compressor.EXTRACTED_ALPHA_CHANNELS_FILE_EXTENTION;
    // File alpha_channes_file = atlasFolder.child(alpha_channes_file_name);
    // alpha_channes_file.writeBytes(bytes);
    // outputAtlas.alpha_channes_file_name = alpha_channes_file.getName();
    // L.d("writing " + bytes.length / 1024 + " kBytes", alpha_channes_file);
    //
    // }
    // }

}

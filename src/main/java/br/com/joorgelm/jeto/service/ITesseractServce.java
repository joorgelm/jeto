package br.com.joorgelm.jeto.service;

import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public interface ITesseractServce {

    String doExtraction() throws TesseractException, IOException;
}

package com.wolkus.ear;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Ear implements Runnable {
	private Thread t;
	private String threadName = "Ear";

	LiveSpeechRecognizer recognizer;

	public Ear() throws IOException {
		Configuration configuration = new Configuration();

		// Set path to acoustic model.
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		// Set path to dictionary.
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		// Set language model.
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		recognizer = new LiveSpeechRecognizer(configuration);
	}

	@Override
	public void run() {
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(true);

		while (true) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpeechResult result = recognizer.getResult();
			System.out.println(result.getHypothesis());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

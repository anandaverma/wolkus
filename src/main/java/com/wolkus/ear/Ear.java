package com.wolkus.ear;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

import com.wolkus.mouth.Mouth;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Ear implements Runnable {
	private Thread t;
	private String threadName = "Ear";

	LiveSpeechRecognizer recognizer;
	Mouth mouth;

	public Ear() throws IOException, EngineException, AudioException, EngineStateError, PropertyVetoException {
		Configuration configuration = new Configuration();

		// Set path to acoustic model.
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		// Set path to dictionary.
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		// Set language model.
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		recognizer = new LiveSpeechRecognizer(configuration);

		mouth = new Mouth();
	}

	@Override
	public void run() {
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(false);

		while (true) {
			SpeechResult result = recognizer.getResult();
			if (null != result) {
				System.out.println(result.getHypothesis());
				try {
					mouth.speak(result.getHypothesis());

				} catch (EngineException | EngineStateError | AudioException | IllegalArgumentException
						| InterruptedException e1) {
					e1.printStackTrace();
					try {
						mouth.terminate();
					} catch (EngineException | EngineStateError e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

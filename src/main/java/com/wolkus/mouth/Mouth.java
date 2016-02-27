package com.wolkus.mouth;

import javax.speech.*;

import java.beans.PropertyVetoException;
import java.util.*;
import javax.speech.synthesis.*;

public class Mouth {

	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;

	public Mouth() throws EngineException, AudioException, EngineStateError, PropertyVetoException {
		String voiceName = "kevin16";
		if (desc == null) {
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			desc = new SynthesizerModeDesc(Locale.US);
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();
			synthesizer.resume();
			SynthesizerModeDesc smd = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = smd.getVoices();
			Voice voice = null;
			
			for (int i = 0; i < voices.length; i++) {
				if (voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
		}
	}

	public void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}

	public void speak(String speakText)
			throws EngineException, AudioException, IllegalArgumentException, InterruptedException {
		synthesizer.speakPlainText(speakText, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
	}
}

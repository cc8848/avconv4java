package com.github.vbauer.avconv4java.option;

import com.github.vbauer.avconv4java.type.AVErrorDetectionType;
import com.github.vbauer.avconv4java.type.AVFormatDebugInfoType;
import com.github.vbauer.avconv4java.type.AVFormatFlagType;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.not;

/**
 * @author Vladislav Bauer
 */

public class AVFormatOptionsTest {

    @Test
    public void testSmokeAVFormatOptions() {
        final List<String> options = AVFormatOptions.create()
            .analyzeDuration(1L)
            .debugInfo(AVFormatDebugInfoType.TS)
            .debugInfo(AVFormatDebugInfoType.TS.getName())
            .decryptionKey("key")
            .errorDetection(AVErrorDetectionType.BIT_STREAM)
            .errorDetection(AVErrorDetectionType.BIT_STREAM.getName())
            .formatFlags(AVFormatFlagType.DISCARD_CORRUPTED)
            .formatFlags(AVFormatFlagType.DISCARD_CORRUPTED.getName())
            .fpsProbeSize(1L)
            .interleavingBufferSize(1L)
            .maxDelay(1L)
            .packetSize(1L)
            .probeSize(1L)
            .realTimeBufferSize(1L)
            .timestampIndexSize(1L)
            .builders(Lists.newArrayList())
            .builders()
            .flags(Lists.newArrayList())
            .flags()
            .build();

        assertThat(options, not(emptyIterable()));
    }

}

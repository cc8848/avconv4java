package com.github.vbauer.avconv4java.core;

import com.github.vbauer.avconv4java.util.AVUtils;
import com.github.vbauer.avconv4java.util.process.ProcessExecutor;
import com.github.vbauer.avconv4java.util.process.ProcessInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a avconv command.
 *
 * @author Vladislav Bauer
 */

public class AVCommand {

    public static final String DEFAULT_TOOL_PATH = "/usr/bin/avconv";
    public static final String SYSTEM_PROPERTY_TOOL_PATH = "AVCONV4JAVA_TOOLPATH";

    private static volatile String globalToolPath = getDefaultToolPath();


    private boolean debug;
    private String toolPath;
    private Long timeout;


    public static String getDefaultToolPath() {
        final String env = AVUtils.getSystemProperty(SYSTEM_PROPERTY_TOOL_PATH);
        return env == null ? DEFAULT_TOOL_PATH : env;
    }

    public static String setGlobalToolPath(final String toolPath) {
        AVCommand.globalToolPath = toolPath;
        return toolPath;
    }

    public static String getGlobalToolPath() {
        return AVCommand.globalToolPath;
    }


    public ProcessInfo run(final AVOptions operation) throws Exception {
        final List<String> flags = operation.build();
        return run(flags);
    }

    public ProcessInfo run(final List<String> flags) throws Exception {
        final List<String> arguments = prepareArguments(flags);
        return ProcessExecutor.execute(arguments, getTimeout(), isDebug());
    }


    public boolean isDebug() {
        return debug;
    }

    public AVCommand setDebug(final Boolean debug) {
        this.debug = Boolean.TRUE.equals(debug);
        return this;
    }

    public Long getTimeout() {
        return timeout;
    }

    public AVCommand setTimeout(final Long timeout) {
        this.timeout = timeout;
        return this;
    }

    public String getToolPath() {
        return toolPath;
    }

    public AVCommand setToolPath(final String toolPath) {
        this.toolPath = toolPath;
        return this;
    }


    /*
     * Internal API.
     */

    protected List<String> prepareArguments(final List<String> flags) {
        final String path = calculateToolPath();
        final List<String> parameters = new LinkedList<>();

        parameters.add(path);
        if (flags != null) {
            parameters.addAll(flags);
        }
        return parameters;
    }

    protected String calculateToolPath() {
        final String toolPath = getToolPath();
        return toolPath == null ? getGlobalToolPath() : toolPath;
    }

}

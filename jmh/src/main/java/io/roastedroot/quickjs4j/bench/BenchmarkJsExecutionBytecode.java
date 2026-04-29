package io.roastedroot.quickjs4j.bench;

import io.roastedroot.quickjs4j.core.Engine;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = {"--enable-native-access=ALL-UNNAMED", "-Dredline.os.name=unsupported"})
public class BenchmarkJsExecutionBytecode {

    Engine engine;
    byte[] compiledCode;

    @Setup(Level.Trial)
    public void setup() {
        engine = Engine.builder().build();
        compiledCode = engine.readCompiled(engine.compile("var x = 1 + 2; x;"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void compileAndExec(Blackhole bh) {
        var codePtr = engine.compile("var x = 1 + 2; x;");
        engine.exec(codePtr);
        engine.free(codePtr);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void execPrecompiled(Blackhole bh) {
        var codePtr = engine.writeCompiled(compiledCode);
        engine.exec(codePtr);
        engine.free(codePtr);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        engine.close();
    }
}

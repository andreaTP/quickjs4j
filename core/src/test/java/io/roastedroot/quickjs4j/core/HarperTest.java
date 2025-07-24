package io.roastedroot.quickjs4j.core;

import com.dylibso.chicory.runtime.ByteArrayMemory;
import com.dylibso.chicory.runtime.HostFunction;
import com.dylibso.chicory.runtime.ImportValues;
import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.wasm.types.FunctionType;
import com.dylibso.chicory.wasm.types.MemoryLimits;
import com.dylibso.chicory.wasm.types.ValType;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HarperTest {

    @Test
    public void testHarper() throws Exception {
        // running wasm-bindgen in QuickJs kinda of works but doesn't seem to be a great idea...
        //
        //        var jsLib =
        //                new String(
        //                                Files.readAllBytes(
        //                                        Path.of(
        //                                                // TODO: maybe better using the _bg
        //
        // "/home/andreatp/workspace/harper/harper-wasm/pkg/harper_wasm.js")),
        //                                StandardCharsets.UTF_8)
        //                        + "const imports = __wbg_get_imports();"
        //                        + "export function invokeImport(name, arg0, arg1, arg2, arg3,
        // arg4, arg5) {"
        //                        + "  return imports.wbg[name](arg0, arg1, arg2, arg3, arg4,
        // arg5);"
        //                        + "}";
        //
        //        var builtins = Builtins.builder("from_java").build();
        //        var invokables =
        //                Invokables.builder("from_js")
        //                        // .add(new GuestFunction("__wbg_get_imports", List.of(),
        // Object.class))
        //                        .add(
        //                                new GuestFunction(
        //                                        "invokeImport",
        //                                        List.of(String.class, Object.class, Object.class),
        //                                        Boolean.class))
        //                        .add(new GuestFunction("debugString", List.of(Object.class,
        // Integer.class, Integer.class), String.class))
        //                        .build();
        //
        //        var jsEngine =
        // Engine.builder().addBuiltins(builtins).addInvokables(invokables).build();
        //
        //        var runner = Runner.builder().withEngine(jsEngine).build();

        // runner.invokeGuestFunction("from_js", "debugString", List.of(1), jsLib);

        // var result = (String) runner.invokeGuestFunction("from_js", "debugString", List.of(1),
        // jsLib);

        // var x = runner.invokeGuestFunction("from_js", "__wbg_get_imports", List.of(), jsLib);
        // var x = runner.invokeGuestFunction("from_js", "invokeImport",
        // List.of("__wbindgen_boolean_get", "True"), jsLib);

        var harperModule = Harper.load();
        //                Parser.parse(
        //                        Path.of(
        //
        // "/home/andreatp/workspace/harper/harper-wasm/pkg/harper_wasm_bg.wasm"));

        //        for (int i = 0; i < harperModule.importSection().importCount(); i++) {
        //            var imprt = (FunctionImport) harperModule.importSection().getImport(i);
        //            var type = harperModule.typeSection().getType(imprt.typeIndex());
        //
        //            System.out.println(imprt.name() + " : " + type);
        //        }

        var imports =
                ImportValues.builder()
                        // __wbindgen_is_string : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_is_string",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_is_string not implemented");
                                        }))
                        // __wbindgen_error_new : (I32,I32) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_error_new",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_error_new not implemented");
                                        }))
                        // __wbindgen_string_new : (I32,I32) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_string_new",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_string_new not implemented");
                                        }))
                        // __wbindgen_number_new : (F64) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_number_new",
                                        FunctionType.of(
                                                List.of(ValType.F64), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_number_new not implemented");
                                        }))
                        // __wbindgen_number_get : (I32,RefNull[-17]) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_number_get",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.ExternRef), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_number_get not implemented");
                                        }))
                        // __wbindgen_string_get : (I32,RefNull[-17]) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_string_get",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.ExternRef), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_string_get not implemented");
                                        }))
                        // __wbindgen_boolean_get : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_boolean_get",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_boolean_get not implemented");
                                        }))
                        // __wbg_suggestion_new : (I32) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_suggestion_new",
                                        FunctionType.of(
                                                List.of(ValType.I32), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_suggestion_new not implemented");
                                        }))
                        // __wbg_lint_new : (I32) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_lint_new",
                                        FunctionType.of(
                                                List.of(ValType.I32), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_lint_new not implemented");
                                        }))
                        // __wbg_mark_7438147ce31e9d4b : (I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_mark_7438147ce31e9d4b",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_mark_7438147ce31e9d4b not implemented");
                                        }))
                        // __wbg_log_cb9e190acc5753fb : (I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_log_cb9e190acc5753fb",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_log_cb9e190acc5753fb not implemented");
                                        }))
                        // __wbg_log_0cc1b7768397bcfe : (I32,I32,I32,I32,I32,I32,I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_log_0cc1b7768397bcfe",
                                        FunctionType.of(
                                                List.of(
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32),
                                                List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_log_0cc1b7768397bcfe not implemented");
                                        }))
                        // __wbg_measure_fb7825c11612c823 : (I32,I32,I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_measure_fb7825c11612c823",
                                        FunctionType.of(
                                                List.of(
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32,
                                                        ValType.I32),
                                                List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_measure_fb7825c11612c823 not"
                                                            + " implemented");
                                        }))
                        // __wbg_new_8a6f238a6ece86ea : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_new_8a6f238a6ece86ea",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_new_8a6f238a6ece86ea not implemented");
                                        }))
                        // __wbg_stack_0ed75d68575b0f3c : (I32,RefNull[-17]) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_stack_0ed75d68575b0f3c",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.ExternRef), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_stack_0ed75d68575b0f3c not implemented");
                                        }))
                        // __wbg_error_7534b8e9a36f1ab4 : (I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_error_7534b8e9a36f1ab4",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_error_7534b8e9a36f1ab4 not implemented");
                                        }))
                        // __wbindgen_is_object : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_is_object",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_is_object not implemented");
                                        }))
                        // __wbindgen_jsval_loose_eq : (RefNull[-17],RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_jsval_loose_eq",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef, ValType.ExternRef),
                                                List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_jsval_loose_eq not implemented");
                                        }))
                        // __wbg_String_8f0eb39a4a4c2f66 : (I32,RefNull[-17]) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_String_8f0eb39a4a4c2f66",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.ExternRef), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_String_8f0eb39a4a4c2f66 not"
                                                            + " implemented");
                                        }))
                        // __wbg_set_3f1d0b984ed272ed : (RefNull[-17],RefNull[-17],RefNull[-17]) ->
                        // nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_set_3f1d0b984ed272ed",
                                        FunctionType.of(
                                                List.of(
                                                        ValType.ExternRef,
                                                        ValType.ExternRef,
                                                        ValType.ExternRef),
                                                List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_set_3f1d0b984ed272ed not implemented");
                                        }))
                        // __wbg_getRandomValues_38097e921c2494c3 : (I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_getRandomValues_38097e921c2494c3",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_getRandomValues_38097e921c2494c3 not"
                                                            + " implemented");
                                        }))
                        // __wbg_get_b9b93047fe3cf45b : (RefNull[-17],I32) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_get_b9b93047fe3cf45b",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef, ValType.I32),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_get_b9b93047fe3cf45b not implemented");
                                        }))
                        // __wbg_length_e2d2a49132c1b256 : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_length_e2d2a49132c1b256",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_length_e2d2a49132c1b256 not"
                                                            + " implemented");
                                        }))
                        // __wbindgen_is_function : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_is_function",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_is_function not implemented");
                                        }))
                        // __wbg_new_5e0be73521bc8c17 : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_new_5e0be73521bc8c17",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_new_5e0be73521bc8c17 not implemented");
                                        }))
                        // __wbg_next_25feadfc0913fea9 : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_next_25feadfc0913fea9",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_next_25feadfc0913fea9 not implemented");
                                        }))
                        // __wbg_next_6574e1a8a62d1055 : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_next_6574e1a8a62d1055",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_next_6574e1a8a62d1055 not implemented");
                                        }))
                        // __wbg_done_769e5ede4b31c67b : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_done_769e5ede4b31c67b",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_done_769e5ede4b31c67b not implemented");
                                        }))
                        // __wbg_value_cd1ffa7b1ab794f1 : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_value_cd1ffa7b1ab794f1",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_value_cd1ffa7b1ab794f1 not implemented");
                                        }))
                        // __wbg_iterator_9a24c88df860dc65 : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_iterator_9a24c88df860dc65",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_iterator_9a24c88df860dc65 not"
                                                            + " implemented");
                                        }))
                        // __wbg_get_67b2ba62fc30de12 : (RefNull[-17],RefNull[-17]) ->
                        // (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_get_67b2ba62fc30de12",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef, ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_get_67b2ba62fc30de12 not implemented");
                                        }))
                        // __wbg_call_672a4d21634d4a24 : (RefNull[-17],RefNull[-17]) ->
                        // (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_call_672a4d21634d4a24",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef, ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_call_672a4d21634d4a24 not implemented");
                                        }))
                        // __wbg_new_405e22f390576ce2 : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_new_405e22f390576ce2",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_new_405e22f390576ce2 not implemented");
                                        }))
                        // __wbg_instanceof_ArrayBuffer_e14585432e3737fc : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_instanceof_ArrayBuffer_e14585432e3737fc",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_instanceof_ArrayBuffer_e14585432e3737fc"
                                                            + " not implemented");
                                        }))
                        // __wbg_set_8fc6bf8a5b1071d1 : (RefNull[-17],RefNull[-17],RefNull[-17]) ->
                        // (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_set_8fc6bf8a5b1071d1",
                                        FunctionType.of(
                                                List.of(
                                                        ValType.ExternRef,
                                                        ValType.ExternRef,
                                                        ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_set_8fc6bf8a5b1071d1 not implemented");
                                        }))
                        // __wbg_getTime_46267b1c24877e30 : (RefNull[-17]) -> (F64)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_getTime_46267b1c24877e30",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.F64)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_getTime_46267b1c24877e30 not"
                                                            + " implemented");
                                        }))
                        // __wbg_new0_f788a2397c7ca929 : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_new0_f788a2397c7ca929",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_new0_f788a2397c7ca929 not implemented");
                                        }))
                        // __wbg_entries_3265d4158b33e5dc : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_entries_3265d4158b33e5dc",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_entries_3265d4158b33e5dc not"
                                                            + " implemented");
                                        }))
                        // __wbg_buffer_609cc3eee51ed158 : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_buffer_609cc3eee51ed158",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_buffer_609cc3eee51ed158 not"
                                                            + " implemented");
                                        }))
                        // __wbg_new_a12002a7f91c75be : (RefNull[-17]) -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_new_a12002a7f91c75be",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef),
                                                List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_new_a12002a7f91c75be not implemented");
                                        }))
                        // __wbg_set_65595bdd868b3009 : (RefNull[-17],RefNull[-17],I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_set_65595bdd868b3009",
                                        FunctionType.of(
                                                List.of(
                                                        ValType.ExternRef,
                                                        ValType.ExternRef,
                                                        ValType.I32),
                                                List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_set_65595bdd868b3009 not implemented");
                                        }))
                        // __wbg_length_a446193dc22c12f8 : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_length_a446193dc22c12f8",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_length_a446193dc22c12f8 not"
                                                            + " implemented");
                                        }))
                        // __wbg_instanceof_Uint8Array_17156bcf118086a9 : (RefNull[-17]) -> (I32)
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbg_instanceof_Uint8Array_17156bcf118086a9",
                                        FunctionType.of(
                                                List.of(ValType.ExternRef), List.of(ValType.I32)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbg_instanceof_Uint8Array_17156bcf118086a9"
                                                            + " not implemented");
                                        }))
                        // __wbindgen_debug_string : (I32,RefNull[-17]) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_debug_string",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.ExternRef), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_debug_string not implemented");
                                        }))
                        // __wbindgen_throw : (I32,I32) -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_throw",
                                        FunctionType.of(
                                                List.of(ValType.I32, ValType.I32), List.of()),
                                        (inst, args) -> {
                                            // Java host implementation
                                            var message =
                                                    inst.memory()
                                                            .readString(
                                                                    (int) args[0], (int) args[1]);
                                            throw new RuntimeException(
                                                    "wbindgen_throw: " + message);
                                            // Testing with QuickJs now
                                            // I need to replace some functions provided by
                                            // wasm-bindgen:
                                            // getStringFromWasm0
                                            // runner.invokeGuestFunction("from_js", "invokeImport",
                                            // List.of("__wbindgen_throw", args[0], args[1]),
                                            // jsLib);
                                            // throw new RuntimeException("unreacheable");
                                        }))
                        // __wbindgen_memory : () -> (RefNull[-17])
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_memory",
                                        FunctionType.of(List.of(), List.of(ValType.ExternRef)),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_memory not implemented");
                                        }))
                        // __wbindgen_init_externref_table : () -> nil
                        .addFunction(
                                new HostFunction(
                                        "wbg",
                                        "__wbindgen_init_externref_table",
                                        FunctionType.of(List.of(), List.of()),
                                        (inst, args) -> {
                                            throw new RuntimeException(
                                                    "__wbindgen_init_externref_table not"
                                                            + " implemented");
                                        }))
                        .build();

        var instance =
                Instance.builder(harperModule)
                        .withMachineFactory(Harper::create)
                        .withMemoryFactory(limits -> new ByteArrayMemory(new MemoryLimits(500)))
                        .withImportValues(imports)
                        .build();

        instance.exports().function("setup").apply();

        var msgStr = "hello world!";
        var msgSize = msgStr.length();
        var msgPtr =
                (int) instance.export("__wbindgen_malloc").apply(msgSize, 1 /* alignment */)[0];
        instance.memory().writeString(msgPtr, msgStr);

        // for (int i = 0; i < harperModule.exportSection().exportCount(); i++) {
        //    System.out.println("export: " + harperModule.exportSection().getExport(i).name());
        //}

        // Dialect
        // American: 0, "0": "American",
        // Language
        // Plain: 0, "0": "Plain",
        System.out.println("Creating a new instance");
        var linterPtr =
                (int) instance.exports().function("linter_new").apply(0 /* American dialect */)[0];
        System.out.println("Created");

        System.out.println("Getting some output");
        var jsonDescriptions =
                instance.export("linter_get_lint_descriptions_as_json").apply(linterPtr);
        var json =
                instance.memory().readString((int) jsonDescriptions[0], (int) jsonDescriptions[1]);
        System.out.println(json);
        System.out.println("done");

        var isEnglish =
                instance.exports()
                        .function("linter_is_likely_english")
                        .apply(linterPtr, msgPtr, msgSize);
        if (isEnglish[0] != 0) {
            System.out.println("Is english!");
        } else {
            System.out.println("Is NOT!");
        }

        var msgStr2 = "uno due tre";
        var msgSize2 = msgStr2.length();
        var msgPtr2 =
                (int) instance.export("__wbindgen_malloc").apply(msgSize2, 1 /* alignment */)[0];
        instance.memory().writeString(msgPtr2, msgStr2);

        var isEnglish2 =
                instance.exports()
                        .function("linter_is_likely_english")
                        .apply(linterPtr, msgPtr2, msgSize2);
        if (isEnglish2[0] != 0) {
            System.out.println("Is english!");
        } else {
            System.out.println("Is NOT!");
        }

        //        System.out.println("runner stdout: " + runner.stdout());
        //        System.out.println("runner stderr: " + runner.stderr());
    }
}

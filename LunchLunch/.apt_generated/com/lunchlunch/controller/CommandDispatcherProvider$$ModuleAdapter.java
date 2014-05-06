// Code generated by dagger-compiler.  Do not edit.
package com.lunchlunch.controller;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class CommandDispatcherProvider$$ModuleAdapter extends ModuleAdapter<CommandDispatcherProvider> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public CommandDispatcherProvider$$ModuleAdapter() {
    super(com.lunchlunch.controller.CommandDispatcherProvider.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, false /*complete*/, true /*library*/);
  }

  @Override
  public CommandDispatcherProvider newModule() {
    return new com.lunchlunch.controller.CommandDispatcherProvider();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, CommandDispatcherProvider module) {
    bindings.contributeProvidesBinding("com.lunchlunch.controller.CommandDispatcherInterface", new ProvideCommandDispatcherProvidesAdapter(module));
  }

  /**
   * A {@code Binding<com.lunchlunch.controller.CommandDispatcherInterface>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<com.lunchlunch.controller.CommandDispatcherInterface>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideCommandDispatcherProvidesAdapter extends ProvidesBinding<CommandDispatcherInterface>
      implements Provider<CommandDispatcherInterface> {
    private final CommandDispatcherProvider module;

    public ProvideCommandDispatcherProvidesAdapter(CommandDispatcherProvider module) {
      super("com.lunchlunch.controller.CommandDispatcherInterface", IS_SINGLETON, "com.lunchlunch.controller.CommandDispatcherProvider", "provideCommandDispatcher");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<com.lunchlunch.controller.CommandDispatcherInterface>}.
     */
    @Override
    public CommandDispatcherInterface get() {
      return module.provideCommandDispatcher();
    }
  }
}

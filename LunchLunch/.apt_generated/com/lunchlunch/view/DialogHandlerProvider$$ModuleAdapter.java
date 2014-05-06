// Code generated by dagger-compiler.  Do not edit.
package com.lunchlunch.view;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class DialogHandlerProvider$$ModuleAdapter extends ModuleAdapter<DialogHandlerProvider> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public DialogHandlerProvider$$ModuleAdapter() {
    super(com.lunchlunch.view.DialogHandlerProvider.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, false /*complete*/, true /*library*/);
  }

  @Override
  public DialogHandlerProvider newModule() {
    return new com.lunchlunch.view.DialogHandlerProvider();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, DialogHandlerProvider module) {
    bindings.contributeProvidesBinding("com.lunchlunch.view.DialogHandlerInterface", new ProviderDialogHandlerProvidesAdapter(module));
  }

  /**
   * A {@code Binding<com.lunchlunch.view.DialogHandlerInterface>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<com.lunchlunch.view.DialogHandlerInterface>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProviderDialogHandlerProvidesAdapter extends ProvidesBinding<DialogHandlerInterface>
      implements Provider<DialogHandlerInterface> {
    private final DialogHandlerProvider module;

    public ProviderDialogHandlerProvidesAdapter(DialogHandlerProvider module) {
      super("com.lunchlunch.view.DialogHandlerInterface", IS_SINGLETON, "com.lunchlunch.view.DialogHandlerProvider", "providerDialogHandler");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<com.lunchlunch.view.DialogHandlerInterface>}.
     */
    @Override
    public DialogHandlerInterface get() {
      return module.providerDialogHandler();
    }
  }
}

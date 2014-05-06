// Code generated by dagger-compiler.  Do not edit.
package com.lunchlunch;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class AndroidModule$$ModuleAdapter extends ModuleAdapter<AndroidModule> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public AndroidModule$$ModuleAdapter() {
    super(com.lunchlunch.AndroidModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, true /*library*/);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, AndroidModule module) {
    bindings.contributeProvidesBinding("android.content.Context", new ProvideApplicationContextProvidesAdapter(module));
    bindings.contributeProvidesBinding("android.location.LocationManager", new ProvideLocationManagerProvidesAdapter(module));
  }

  /**
   * A {@code Binding<android.content.Context>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<android.content.Context>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideApplicationContextProvidesAdapter extends ProvidesBinding<android.content.Context>
      implements Provider<android.content.Context> {
    private final AndroidModule module;

    public ProvideApplicationContextProvidesAdapter(AndroidModule module) {
      super("android.content.Context", IS_SINGLETON, "com.lunchlunch.AndroidModule", "provideApplicationContext");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.content.Context>}.
     */
    @Override
    public android.content.Context get() {
      return module.provideApplicationContext();
    }
  }

  /**
   * A {@code Binding<android.location.LocationManager>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<android.location.LocationManager>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideLocationManagerProvidesAdapter extends ProvidesBinding<android.location.LocationManager>
      implements Provider<android.location.LocationManager> {
    private final AndroidModule module;

    public ProvideLocationManagerProvidesAdapter(AndroidModule module) {
      super("android.location.LocationManager", IS_SINGLETON, "com.lunchlunch.AndroidModule", "provideLocationManager");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.location.LocationManager>}.
     */
    @Override
    public android.location.LocationManager get() {
      return module.provideLocationManager();
    }
  }
}
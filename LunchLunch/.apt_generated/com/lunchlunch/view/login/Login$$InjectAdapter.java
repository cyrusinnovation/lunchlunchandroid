// Code generated by dagger-compiler.  Do not edit.
package com.lunchlunch.view.login;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<Login>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code Login} and its
 * dependencies.
 *
 * Being a {@code Provider<Login>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<Login>} and handling injection
 * of annotated fields.
 */
public final class Login$$InjectAdapter extends Binding<Login>
    implements Provider<Login>, MembersInjector<Login> {
  private Binding<Provider<com.lunchlunch.controller.CommandDispatcherInterface>> commandDispatcherProvider;
  private Binding<Provider<com.lunchlunch.view.DialogHandlerInterface>> dialogHandler;
  private Binding<Provider<com.lunchlunch.webcomm.login.LoginHelperInterface>> loginHelper;

  public Login$$InjectAdapter() {
    super("com.lunchlunch.view.login.Login", "members/com.lunchlunch.view.login.Login", NOT_SINGLETON, Login.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    commandDispatcherProvider = (Binding<Provider<com.lunchlunch.controller.CommandDispatcherInterface>>) linker.requestBinding("javax.inject.Provider<com.lunchlunch.controller.CommandDispatcherInterface>", Login.class, getClass().getClassLoader());
    dialogHandler = (Binding<Provider<com.lunchlunch.view.DialogHandlerInterface>>) linker.requestBinding("javax.inject.Provider<com.lunchlunch.view.DialogHandlerInterface>", Login.class, getClass().getClassLoader());
    loginHelper = (Binding<Provider<com.lunchlunch.webcomm.login.LoginHelperInterface>>) linker.requestBinding("javax.inject.Provider<com.lunchlunch.webcomm.login.LoginHelperInterface>", Login.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(commandDispatcherProvider);
    injectMembersBindings.add(dialogHandler);
    injectMembersBindings.add(loginHelper);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<Login>}.
   */
  @Override
  public Login get() {
    Login result = new Login();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<Login>}.
   */
  @Override
  public void injectMembers(Login object) {
    object.commandDispatcherProvider = commandDispatcherProvider.get();
    object.dialogHandler = dialogHandler.get();
    object.loginHelper = loginHelper.get();
  }

}

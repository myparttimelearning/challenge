package com.rumodigi.fanduelmobilechallenge.presentation.di;

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface ContainsComponent<C> {
    C getComponent();
}

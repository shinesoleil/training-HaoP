package com.tw.ioc;

import com.tw.ioc.Dependencies.Dependency;
import com.tw.ioc.Dependencies.DependencyImpl;
import com.tw.ioc.Dependencies.SecondDependency;
import com.tw.ioc.Dependencies.SecondDependencyImpl;
import com.tw.ioc.classes.ClassTwoDependencies;
import com.tw.ioc.classes.NormalClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DITest {

    private Dependency dependency;
    private NormalClass normalClass;

    @Test
    public void should_instantiate_registered_class() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);

        dependency = (Dependency) c.createInstance(Dependency.class);

        NormalClass normalClass = new NormalClass(dependency);

        assertThat(normalClass.NormalMethod(), is("DI success"));
    }

    @Test
    public void should_instantiate_registered_class_with_dependency() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);
        c.registerImplementation(NormalClass.class);

        normalClass = (NormalClass) c.createInstance(NormalClass.class);

        assertThat(normalClass.NormalMethod(), is("DI success"));
    }

    @Test
    public void should_instantiate_registered_class_with_several_dependencies() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);
        c.registerImplementation(SecondDependency.class, SecondDependencyImpl.class);
        c.registerImplementation(ClassTwoDependencies.class);

        ClassTwoDependencies classTwoDependencies = (ClassTwoDependencies) c.createInstance(ClassTwoDependencies.class);

        assertThat(classTwoDependencies.method(), is("DI success, Second DI success"));
    }


}

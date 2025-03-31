package com.uoc.pr1

import com.uoc.pr1.data.DataSource
import org.junit.Test
import org.junit.Assert.*

class DataSourceTest {

    @Test
    fun testSeminarRecommendation() {

        //BEGIN-CODE-UOC-7

        // 7.1. We create a DataSource instance
        val dataSource = DataSource()

        // 7.2. We create the Test Case with the given data in the statement
        val result = dataSource.seminarRecommendation(30, "IT", false)

        // 7.3. We added the result specified in the statement
        assertEquals("advanced", result)

        /*
        7.4 Test Results and answers:

        We don´t need to run the emulator because we are testing a single method,
        and we are not interacting with any Android component or UI.
        So, since this is a function that only depends on the input parameters, the unit
        test run on the JVM and not in the device or emulator.

        7.4.1 After running the test with the given parameters, our test will fail:

        Expected :advanced
        Actual   :beginner
        <Click to see difference>

        org.junit.ComparisonFailure: expected:<[advanced]> but was:<[beginner]>
            at org.junit.Assert.assertEquals(Assert.java:117)
            at org.junit.Assert.assertEquals(Assert.java:146)
            at com.uoc.pr1.DataSourceTest.testSeminarRecommendation(DataSourceTest.kt:18)
            at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
            at java.base/java.lang.reflect.Method.invoke(Unknown Source)
            at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
            at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
            at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
            at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
            at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
            at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
            at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
            at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
            at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
            at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:112)
            at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
            at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:40)
            at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:54)
            at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:53)
            at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
            at java.base/java.lang.reflect.Method.invoke(Unknown Source)
            at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
            at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
            at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
            at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
            at jdk.proxy1/jdk.proxy1.$Proxy4.processTestClass(Unknown Source)
            at org.gradle.api.internal.tasks.testing.worker.TestWorker$2.run(TestWorker.java:183)
            at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
            at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
            at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
            at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
            at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:121)
            at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
            at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
            at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
        */
    }

    //END-CODE-UOC-7

}
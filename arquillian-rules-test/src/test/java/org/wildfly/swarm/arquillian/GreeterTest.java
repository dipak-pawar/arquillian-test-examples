package org.wildfly.swarm.arquillian;

import javax.inject.Inject;
import org.jboss.arquillian.junit.ArquillianTest;
import org.jboss.arquillian.junit.ArquillianTestClass;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@DefaultDeployment(type = DefaultDeployment.Type.WAR)
public class GreeterTest {

   @ClassRule
   public static ArquillianTestClass arquillianTestClass = new ArquillianTestClass();

   @Rule
   public ArquillianTest arquillianTest = new ArquillianTest();

   @Inject
   Greeter greeter;

   @Test
   public void should_greet_earthlings() throws Exception {
      String name = "Earthlings";
      Assert.assertEquals("Hello, " + name, greeter.greet(name));
   }
}


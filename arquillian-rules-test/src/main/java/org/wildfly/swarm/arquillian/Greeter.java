package org.wildfly.swarm.arquillian;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Greeter {

   public String greet(String userName) {
      return "Hello, " + userName;
   }
}

package org.arquillian.cube.openshift.standalone;

import java.io.IOException;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.arquillian.cube.kubernetes.annotations.Named;
import org.arquillian.cube.kubernetes.annotations.PortForward;
import org.arquillian.cube.openshift.impl.requirement.RequiresOpenshift;
import org.jboss.arquillian.junit.ArquillianTest;
import org.jboss.arquillian.junit.ArquillianTestClass;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiresOpenshift
public class HelloWorldTest {

   @ClassRule
   public static ArquillianTestClass arquillianTestClass = new ArquillianTestClass();

   @Rule
   public ArquillianTest arquillianTest = new ArquillianTest();

   @Named("hello-openshift-service")
   @PortForward
   @ArquillianResource
   URL url;

   @Test
   public void should_show_hello_world() throws IOException {
      assertThat(url).isNotNull();
      OkHttpClient okHttpClient = new OkHttpClient();
      Request request = new Request.Builder().get().url(url).build();
      Response response = okHttpClient.newCall(request).execute();

      assertThat(response).isNotNull();
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().string()).isEqualTo("Hello OpenShift!\n");
   }
}

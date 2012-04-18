/**
 * junixsocket
 *
 * Copyright (c) 2009 NewsClub, Christian Kohlschütter
 *
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.newsclub.net.unix.demo.rmi;

import java.rmi.registry.Registry;

import org.newsclub.net.unix.rmi.AFUNIXNaming;

/**
 * A simple RMI client. Locates the RMI registry via AF_UNIX sockets and
 * calls {@link HelloWorld#hello()}.
 * 
 * @author Christian Kohlschütter
 */
public class SimpleRMIClient {
    public static void main(String[] args) throws Exception {
        AFUNIXNaming naming = AFUNIXNaming.getInstance();
        
        System.out.println("Locating registry...");
        final Registry registry = naming.getRegistry();
        System.out.println(registry);
        System.out.println();
        HelloWorld obj = (HelloWorld) registry.lookup("helloWorld");
        System.out.println("HelloWorld instance: "+obj);
        System.out.println();
        System.out.println("Calling HelloWorld#hello()...");
        System.out.println(obj.hello());
    }
}

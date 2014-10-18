package com.rolandoislas.math.util;
 
public class OS {
       
        private static String OSS;
       
        public OS() {
                OSS = System.getProperty("os.name").toLowerCase();
        }
       
        public static String getSys() {
                if(isWindows()) {
                        return "win";
                } else if(isMac()) {
                        return "mac";
                } else if(isUnix()) {
                        return "nix";
                }
                return null;
        }
 
        public static boolean isUnix() {
                return (OSS.indexOf("nix") >= 0 || OSS.indexOf("nux") >= 0 || OSS.indexOf("aix") > 0 );
        }
 
        public static boolean isMac() {
                return (OSS.indexOf("mac") >= 0);
        }
 
        public static boolean isWindows() {
                return (OSS.indexOf("win") >= 0);
        }
        
       
}
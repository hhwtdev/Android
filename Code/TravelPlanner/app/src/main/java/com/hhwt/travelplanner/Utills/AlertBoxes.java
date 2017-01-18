/*  * Created by Mathankumar  * Copyright 2015 Google Inc. All rights reserved.  *  * Licensed under the Apache License, Version 2.0 (the "License");  * you may not use this file except in compliance with the License.  * You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */   /*  * Created by Mathankumar  * Copyright 2015 Google Inc. All rights reserved.  *  * Licensed under the Apache License, Version 2.0 (the "License");  * you may not use this file except in compliance with the License.  * You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */   package com.hhwt.travelplanner.Utills;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class AlertBoxes {
    private static SQLiteDatabase _SQLDB;

    public static enum AlertCondition {
        Internet, UnameEmpty, PassEmpty, Both, Exception, PassConfirmEmpty, PasswordMismatch, PassChangeSuccess, PassNotChanged, MessageTO, MessSubject, MessContent, MessSuccess, MEssFailed, MessArchieved, MessReturnInbox
    }

    public static void sLoginAlert(Activity activity,
                                   AlertCondition alertCondition) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog.Builder builders = new AlertDialog.Builder(activity);

        switch (alertCondition) {
            case Internet:
                builder.setTitle("Internet Connection\n")
                        .setMessage(
                                "\nYour device has lost its Internet connection, please check your internet settings and try again\n");
                break;
            case UnameEmpty:
                builder.setTitle("Username missing\n").setMessage(
                        "\nPlease enter the username\n");
                break;
            case PassEmpty:
                builder.setTitle("Password missing\n").setMessage(
                        "\nPlease enter the password \n");
                break;
            case Both:
                builder.setTitle("Login\n").setMessage(
                        "\nInvalid user name or password\n");
                break;
            case Exception:
                builders.setTitle("Login\n").setMessage(
                        "\nUnable to contact Pitsco, please try again\n");
                break;
        }

        builder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builders.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sInternetAlert(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("No internet connection\n");
        builder.setMessage("\nPlease turn on your internet and try again.\n")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sDownloadAlert(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Version Alert\n");
        builder.setMessage(
                "\nThe version of this lesson is not supported for Android devices .\n")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sDevelopmentAlert(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Development alert\n");
        builder.setMessage("\nDevelopment under construction .\n")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sArcheiveAlert(Activity activity, AlertCondition alertCondition) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Message\n");

        switch (alertCondition) {
            case MessArchieved:
                builder.setMessage("\nMessage archived succesfully .\n");
                break;

            case MessReturnInbox:
                builder.setMessage("\nMessage returned to inbox succesfully .\n");
                break;
        }

        builder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sLogutAlert(final Activity mActivity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mActivity);
        alertDialogBuilder
                .setTitle("Logout confirmation\n")
                .setMessage("\nAre you sure you'd like to log-out?\n")
                .setCancelable(false)
                .setPositiveButton("Logout",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                              /*  Databaseconnector db = new Databaseconnector(
                                        mActivity);
                                _SQLDB = db.getWritableDatabase();*/
                                mActivity.deleteDatabase("shortfilemakers.sqlite");
                                mActivity.deleteDatabase("webview.db");
                                mActivity.deleteDatabase("webviewCache.db");


                                try {
                                    Runtime.getRuntime().exec(String.format("rm -rf %s", "data/data/com.kalaam.mathan.mydream/"));
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

								
								
							/*	try {
                               //     trimCache(mActivity);
							       // Toast.makeText(this,"onDestroy " ,Toast.LENGTH_LONG).show();
							    } catch (Exception e) {
							        // TODO Auto-generated catch block
							        e.printStackTrace();
							    }*/
                                File del = new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Lessons");
                                File delete = new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Remedation");

                                File deleteLMSzip = new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/LMS.zip");
                                File deleteREMzip = new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/REM.zip");

                                boolean success = del.delete();
                                boolean successrem = delete.delete();
                                boolean successLMSzip = deleteLMSzip.delete();
                                boolean successREMzip = deleteREMzip.delete();

                                if (!success) {
                                    deleteNon_EmptyDir(del);
                                }

                                if (!successrem) {
                                    deleteNon_EmptyDir(delete);

                                }
                                if (!successLMSzip) {
                                    deleteNon_EmptyDir(deleteLMSzip);

                                }
                                if (!successREMzip) {
                                    deleteNon_EmptyDir(deleteREMzip);

                                }
                                android.os.Process.killProcess(android.os.Process.myPid());
                   /*             Intent intent1 = new Intent(mActivity,
                                        Login_page.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent1.putExtra("EXIT", true);
                                mActivity.startActivity(intent1);*/
                                mActivity.finish();
                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

        //
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

	/*public static void sAlertSession(final Activity mActivity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
	
		
			builder.setTitle("Oops!\n");
		
			

		builder.setMessage("\n Your session has been expired \n")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}*/

    public static void sAlert_Pass_Message(final Activity mActivity,
                                           AlertCondition alertCondition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        switch (alertCondition) {
            case PassEmpty:
                builder.setTitle("Required field missing\n").setMessage(
                        "\n Enter your new password \n");
                break;
            case PassConfirmEmpty:
                builder.setTitle("Required field missing\n").setMessage(
                        "\n Enter your confirm password \n");
                break;
            case PasswordMismatch:
                builder.setTitle("Change password\n").setMessage(
                        "\n Given password is mismatch \n");
                break;
            case PassNotChanged:
                builder.setTitle("Change password\n").setMessage(
                        "\n Password not changed \n");
                break;
            case PassChangeSuccess:
                builder.setTitle("Change password\n").setMessage(
                        "\n Password has been changed successfully \n");
                break;
            case MessageTO:
                builder.setTitle("Message\n").setMessage(
                        "\n Please specify recipient \n");
                break;
            case MessSubject:
                builder.setTitle("Message\n")
                        .setMessage(
                                "\n Subject looks empty. Write a subject for your message \n");
                break;
            case MessContent:
                builder.setTitle("Message\n").setMessage(
                        "\n Please, Enter your message \n");
                break;
            case MessSuccess:
                builder.setTitle("Message\n").setMessage(
                        "\n Your message sent successfully \n");
                break;
            case MEssFailed:
                builder.setTitle("Message\n").setMessage(
                        "\n Your message sending failed \n");
                break;
        }

        builder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


    @SuppressLint("SdCardPath")
    public static void trimCache(Context context) {

        try {
            File dir = context.getCacheDir();
            File appDir = new File(dir.getParent());
            if (appDir.exists()) {
                String[] children = appDir.list();
                for (String s : children) {
                    File f = new File(appDir, s);
                    if (deleteDir(f))
                        Log.i("Deleted", String.format("** DELETED -> (%s) **", f.getAbsolutePath()));
                }
            }
        } catch (Exception e) {

            // TODO: handle exception
        }
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    public static boolean deleteNon_EmptyDir(File dir) {
        if (dir.isDirectory()) {

            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteNon_EmptyDir(new File(dir, children[i]));
                if (!success) {
                    System.out.println("Deletion of directory failed!");
                    return false;
                }
            }

        }
        return dir.delete();
    }
    /**
     * Call this method to delete any cache created by app
     * @param context context for your application
     */


}

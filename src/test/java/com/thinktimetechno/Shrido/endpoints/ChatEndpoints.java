package com.thinktimetechno.Shrido.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

import com.thinktimetechno.utils.FailedApiTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class ChatEndpoints extends BaseEndpoints {

    private RequestSpecification requestSpecification;

    public Response result;

    public static String authToken;


    // ==================== POST APIs ====================

    public void sendPostRequestWithPayload(String jsonFileName) throws IOException {

        try {

            // Select endpoint based on payload filename
            switch (jsonFileName) {

                case "Get All Chat.json":

                    application_ENDPOINT_PATH = "/api/chat/all_chat";
                    break;


                case "Delete Chat (hide for user).json":

                    application_ENDPOINT_PATH = "/api/chat/delete";
                    break;


                case "Mark Chat Read.json":

                    application_ENDPOINT_PATH = "/api/chat/mark-read";
                    break;


                case "Send Chat Message.json":

                    application_ENDPOINT_PATH = "/api/chat";
                    break;


                default:

                    throw new IllegalArgumentException(
                            "Endpoint not defined for file: " + jsonFileName);
            }


            this.apiNameIdentifier = jsonFileName.replace(".json", "");


            // Prepare request
            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            // Read JSON payload
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/Payloads/ChatPayloads/"
                    + jsonFileName;

            String jsonContent = new String(
                    Files.readAllBytes(Paths.get(filePath)),
                    StandardCharsets.UTF_8
            );

            JSONObject jsonObject = new JSONObject(jsonContent);


            // ==================== SET REQUEST BODY VALUES ====================

            if (application_ENDPOINT_PATH.equals("/api/chat/all_chat")) {

                jsonObject.put("user_id", BaseEndpoints.userId);
                jsonObject.put("trip_id", BaseEndpoints.tripId);

            } else if (application_ENDPOINT_PATH.equals("/api/chat/delete")) {

                // Set user ID dynamically
                jsonObject.put("user_id", BaseEndpoints.userId);

            } else if (application_ENDPOINT_PATH.equals("/api/chat/mark-read")) {

                // Use room_id captured from the Delete Chat response
                if (BaseEndpoints.roomId == null
                        || BaseEndpoints.roomId.trim().isEmpty()) {

                    throw new IllegalStateException(
                            "room_id is missing. Run Delete Chat first "
                                    + "and capture room_id from its response."
                    );
                }

                jsonObject.put("room_id", BaseEndpoints.roomId);

                System.out.println(
                        "Mark Chat Read room_id: " + BaseEndpoints.roomId
                );

            } else if (application_ENDPOINT_PATH.equals("/api/chat")) {

                // Set recipient user ID dynamically
                jsonObject.put("to_user", BaseEndpoints.peerUserId);
            }


            // ==================== SEND POST REQUEST ====================

            result = requestSpecification
                    .body(jsonObject.toString())
                    .post(application_ENDPOINT_PATH);


            // ==================== CAPTURE DELETE CHAT ROOM ID ====================

            if (application_ENDPOINT_PATH.equals("/api/chat/delete")
                    && result.getStatusCode() == 200) {

                JSONObject responseJson =
                        new JSONObject(result.getBody().asString());

                if (responseJson.has("data")
                        && !responseJson.isNull("data")) {

                    JSONObject dataObject =
                            responseJson.getJSONObject("data");

                    if (dataObject.has("room_id")
                            && !dataObject.isNull("room_id")) {

                        BaseEndpoints.roomId =
                                dataObject.getString("room_id");

                        System.out.println(
                                "Captured room_id from Delete Chat: "
                                        + BaseEndpoints.roomId
                        );

                    } else {

                        throw new IllegalStateException(
                                "Delete Chat response does not contain data.room_id"
                        );
                    }

                } else {

                    throw new IllegalStateException(
                            "Delete Chat response does not contain data object"
                    );
                }
            }


            // ==================== PRINT RESPONSE ====================

            System.out.println("API: " + application_ENDPOINT_PATH);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println(
                    "Response: " + result.getBody().asPrettyString()
            );


        } catch (IOException e) {

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    e.getClass().getSimpleName()
            );

            throw e;

        } catch (RuntimeException e) {

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    e.getClass().getSimpleName()
            );

            throw e;
        }
    }



    // ==================== GET APIs ====================

    public Response sendGetRequest(String APIName) {

        try {

            // Select endpoint
            switch (APIName) {

                case "Chat List":

                    application_ENDPOINT_PATH = "/api/chat/list";
                    break;


                default:

                    throw new IllegalArgumentException(
                            "Endpoint not defined for API: " + APIName);
            }


            this.apiNameIdentifier = APIName;


            // Prepare request
            requestSpecification =
                    getRequestWithJSONHeader(application_ENDPOINT_PATH);


            // Send GET request
            result = requestSpecification
                    .get(application_ENDPOINT_PATH);


            // Print response
            System.out.println("API: " + application_ENDPOINT_PATH);

            System.out.println("Status Code: " + result.getStatusCode());

            System.out.println(
                    "Response: " + result.getBody().asPrettyString()
            );


            return result;


        } catch (RuntimeException e) {

            FailedApiTracker.logFailure(
                    apiNameIdentifier != null
                            ? apiNameIdentifier
                            : application_ENDPOINT_PATH,
                    e.getClass().getSimpleName()
            );

            throw e;
        }
    }

}
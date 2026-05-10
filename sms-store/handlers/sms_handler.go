package handlers

import (
	"context"
	"encoding/json"
	"net/http"
	"strings"

	"sms-store/database"
	"sms-store/models"

	"go.mongodb.org/mongo-driver/v2/bson"
)

func GetUserMessages(w http.ResponseWriter, r *http.Request) {

	// extract user id from URL
	parts := strings.Split(r.URL.Path, "/")
	userId := parts[3]

	collection := database.DB.Collection("messages")

	cursor, err := collection.Find(context.Background(), bson.M{"userId": userId})
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	var messages []models.SMS

	err = cursor.All(context.Background(), &messages)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	json.NewEncoder(w).Encode(messages)
}

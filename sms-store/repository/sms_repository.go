package repository

import (
	"context"
	"sms-store/database"
	"sms-store/models"
)

func SaveSMS(sms models.SMS) error {

	collection := database.DB.Collection("sms")

	_, err := collection.InsertOne(context.Background(), sms)

	return err
}

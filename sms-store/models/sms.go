package models

type SMS struct {
	PhoneNumber string `json:"phoneNumber" bson:"phoneNumber"`
	Message     string `json:"message" bson:"message"`
}

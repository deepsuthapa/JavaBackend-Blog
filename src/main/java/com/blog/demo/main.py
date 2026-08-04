from pymongo import MongoClient

client = MongoClient('mongodb+srv://roystond402_db_user:62yNFo1lkfAMxbIm@users.qfkokaq.mongodb.net/?appName=users')

db = client['Deepsu']
users = db['users']

user = {
    "name":"Roy",
    "email":"enrjwejr@gmail.com",
    "password":"Roy",
    "courses":["CS801,CS802"]
}

course1 = {
    "code":"CS801",
    "name":"Python"
    }

course2 = {
    "code":"CS802",
    "name":"Java"
    }

db.create_collection("courses")
courses = db["courses"]
courses.insert_one(course1)
courses.insert_one(course2)

print("Student Name | Course Code | Course Name")
for user in users:

users.insert_one(user)
print(users.find_one({"name":"Roy"}))
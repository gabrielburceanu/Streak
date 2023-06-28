db = db.getSiblingDB('streak');
db.createCollection("users");
db.users.insertOne({
    "username": process.env.STREAK_USER,
    "passwordHash": process.env.STREAK_PASS_HASH
});

db.createUser(
        {
            user: process.env.MONGO_AUTH_USER,
            pwd: process.env.MONGO_AUTH_PASS,
            roles: [
                {
                    role: "readWrite",
                    db: "streak"
                }
            ]
        }
);

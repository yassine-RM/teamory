-- USERS (linked with Keycloak UUIDs)
CREATE TABLE users (
                       id VARCHAR(36) PRIMARY KEY,
                       username VARCHAR(100),
                       email VARCHAR(150),
                       full_name VARCHAR(150)
);

-- TEAMS
CREATE TABLE teams (
                       id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       description TEXT
);

-- TEAM MEMBERS
CREATE TABLE team_members (
                              team_id UUID NOT NULL,
                              user_id VARCHAR(36) NOT NULL,
                              PRIMARY KEY (team_id, user_id),
                              FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- TASKS
CREATE TABLE tasks (
                       id UUID PRIMARY KEY,
                       title VARCHAR(150) NOT NULL,
                       description TEXT,
                       status VARCHAR(50),
                       team_id UUID,
                       assigned_to VARCHAR(36),
                       due_date DATE,
                       created_at TIMESTAMP,
                       FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL,
                       FOREIGN KEY (assigned_to) REFERENCES users(id) ON DELETE SET NULL
);

-- COMMENTS
CREATE TABLE comments (
                          id UUID PRIMARY KEY,
                          content TEXT,
                          task_id UUID,
                          user_id VARCHAR(36),
                          created_at TIMESTAMP,
                          FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- ACTIVITY LOGS
CREATE TABLE activity_logs (
                               id UUID PRIMARY KEY,
                               action VARCHAR(100),
                               actor_id VARCHAR(36),
                               details TEXT,
                               timestamp TIMESTAMP,
                               FOREIGN KEY (actor_id) REFERENCES users(id) ON DELETE SET NULL
);

-- ===============================
-- DROP TABLES IF THEY EXIST (in reverse dependency order)
-- ===============================

DROP TABLE IF EXISTS training_session CASCADE;
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS memberships CASCADE;

-- ===============================
-- APP_USER TABLE
-- ===============================
CREATE TABLE IF NOT EXISTS app_user (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_password TEXT NOT NULL,
    user_phone VARCHAR(15),
    user_address TEXT,
    user_role VARCHAR(20) NOT NULL CHECK (user_role IN ('member', 'trainer', 'admin')),
);

-- ===============================
-- MEMBERSHIP TABLE 
-- ===============================
-------------
CREATE TABLE memberships (
    id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50),
    membership_description TEXT,
    membership_cost NUMERIC(10, 2),
    customer_id INTEGER,
    valid_from DATE,
    valid_until DATE,
    credits_remaining INTEGER
);

-- ===============================
-- TRAINING_SESSION TABLE 
-- ===============================
CREATE TABLE IF NOT EXISTS training_session (
    session_id SERIAL PRIMARY KEY,
    session_type VARCHAR(50) NOT NULL,
    session_details TEXT,
    instructor_id INT REFERENCES app_user(id) ON DELETE SET NULL,
    starts_at TIME,
    ends_at TIME,
    session_day DATE
);









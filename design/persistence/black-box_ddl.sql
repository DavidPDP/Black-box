-- -----------------------------------------------------
-- Sgco schema
-- -----------------------------------------------------
CREATE SCHEMA sgco

    -- -----------------------------------------------------
    -- Sequence sq_001_buses
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_buses
        INCREMENT BY 1
        MINVALUE 0
        MAXVALUE 2147483647
        START WITH 1
        NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_buses
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_buses (
        busid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_buses'),
        busnumber BIGINT NULL,
        identification VARCHAR(6) NULL,
        bustypeid BIGINT NULL,
        planversionid BIGINT NULL,
        PRIMARY KEY (busid))

    -- -----------------------------------------------------
    -- Sequence sq_001_lines
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_lines
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_lines
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_lines (
        lineid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_lines'),
        planversionid BIGINT NULL,
        shortname VARCHAR(10) NULL,
        description VARCHAR(100) NULL,
        PRIMARY KEY (lineid))

    -- -----------------------------------------------------
    -- Sequence sq_001_calendars
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_calendars
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_calendars
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_calendars (
        calendarid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_calendars'),
        operationday TIMESTAMP NULL,
        scheduletypeid BIGINT NULL,
        planversionid BIGINT NULL,
        PRIMARY KEY (calendarid))

    -- -----------------------------------------------------
    -- Sequence sq_001_linestops
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_linestops
        INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_linestops
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_linestops (
	linestopid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_linestops'),
	stopsequence BIGINT NULL,
	orientation BIGINT NULL,
	lineid BIGINT NULL,
	stopid BIGINT NULL,
	planversionid BIGINT NULL,
	linevariant BIGINT NULL,
	registerdate TIMESTAMP NULL,
	linevarianttype BIGINT NULL,
	PRIMARY KEY (linestopid))

    -- -----------------------------------------------------
    -- Sequence sq_001_scheduletypes
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_scheduletypes
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_scheduletypes
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_scheduletypes (
	scheduletypeid BIGINT NULL DEFAULT NEXTVAL ('sgco.sq_001_scheduletypes'),
	planversionid BIGINT NULL,
	shortname VARCHAR(10) NULL,
	description VARCHAR(100) NULL,
	PRIMARY KEY (scheduletypeid))

    -- -----------------------------------------------------
    -- Sequence sq_001_stops
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_stops
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_stops 
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_stops (
	stopid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_stops'),
	planversionid BIGINT NULL,
	shortname VARCHAR(10) NULL,
	longname VARCHAR(100) NULL,
	gps_x DOUBLE PRECISION NULL,
	gps_y DOUBLE PRECISION NULL,
	decimallongitude DOUBLE PRECISION NULL,
	decimallatitude DOUBLE PRECISION NULL,
	PRIMARY KEY (stopid))

    -- -----------------------------------------------------
    -- Sequence sq_001_tasks
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_tasks
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_tasks
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_tasks (
	taskid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_tasks'),
	scheduletypeid BIGINT NULL,
	lines_lineid BIGINT NULL,
	planversionid BIGINT NULL,
	PRIMARY KEY (taskid))

    -- -----------------------------------------------------
    -- Sequence sq_001_trips
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_trips
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_001_trips
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_trips (
	tripid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.t_001_trips'),
	planversionid BIGINT NULL,
	triptypeid BIGINT NULL,
	scheduletypeid BIGINT NULL,
	tripsequence BIGINT NULL,
	starttime VARCHAR(6) NULL,
	taskid BIGINT NULL,
	lineid BIGINT NULL,
	startstopid BIGINT NULL,
	endstopid BIGINT NULL,
	description VARCHAR(256) NULL,
	orientation SMALLINT NULL,
	linevariant SMALLINT NULL,
	registerdate TIMESTAMP NULL,
	scheduleprofileid BIGINT NULL,
	PRIMARY KEY (tripid))

    -- -----------------------------------------------------
    -- Sequence sq_001_planversions
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_001_planversions
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE;

    -- -----------------------------------------------------
    -- Table t_001_planversions
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_001_planversions (
	planversionid BIGINT NOT NULL DEFAULT NEXTVAL ('sgco.sq_001_planversions'),
	activationdate TIMESTAMP NULL,
	creationdate TIMESTAMP NULL,
	PRIMARY KEY (planversionid))

;

-- -----------------------------------------------------
-- Policies schema
-- -----------------------------------------------------
CREATE SCHEMA policies

    -- -----------------------------------------------------
    -- Sequence sq_002_services
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_services
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_services
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_services (
	id INT NOT NULL DEFAULT NEXTVAL ('policies.sq_002_services'),
	name VARCHAR(45) NOT NULL,
	uri VARCHAR(300) NOT NULL,
	protocol VARCHAR(45) NOT NULL,
	is_internal BOOLEAN NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT uq_t_002_services_name UNIQUE (name),
	CONSTRAINT uq_t_002_services_uri UNIQUE (uri))

    -- -----------------------------------------------------
    -- Sequence sq_002_roles
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_roles
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_roles
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_roles (
	id INT NOT NULL DEFAULT NEXTVAL ('policies.sq_002_roles'),
	name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT uq_t_002_roles_name UNIQUE (name))

    -- -----------------------------------------------------
    -- Table t_002_permissions
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_permissions (
	service INT NOT NULL,
	role INT NOT NULL,
	PRIMARY KEY (service, role),
	CONSTRAINT fk_t_002_permissions_t_002_services FOREIGN KEY (service) REFERENCES policies.t_002_services (id),
	CONSTRAINT fk_t_002_permissions_t_002_roles FOREIGN KEY (role) REFERENCES policies.t_002_roles (id))

    -- -----------------------------------------------------
    -- Indexes t_002_permissions
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_permissions_service ON policies.t_002_permissions (service)
    CREATE INDEX idx_t_002_permissions_role ON policies.t_002_permissions (role)


    -- -----------------------------------------------------
    -- Sequence sq_002_users
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_users
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_users
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_users (
	id INT NOT NULL DEFAULT NEXTVAL ('policies.sq_002_users'),
	account_name VARCHAR(100) NOT NULL,
	is_active BOOLEAN NOT NULL,
	password VARCHAR(400) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT uq_t_002_users_account_name UNIQUE (account_name))

    -- -----------------------------------------------------
    -- Table t_002_users_roles
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_users_roles (
	role INT NOT NULL,
	owner INT NOT NULL,
	PRIMARY KEY (role, owner),
	CONSTRAINT fk_t_002_users_roles_t_002_roles FOREIGN KEY (role) REFERENCES policies.t_002_roles (id),
	CONSTRAINT fk_t_002_users_roles_t_002_users FOREIGN KEY (owner) REFERENCES policies.t_002_users (id))

    -- -----------------------------------------------------
    -- Indexes t_002_users_roles
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_users_roles_role ON policies.t_002_users_roles (role)
    CREATE INDEX idx_t_002_users_roles_user ON policies.t_002_users_roles (owner)

    -- -----------------------------------------------------
    -- Sequence sq_002_settings 
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_settings 
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_settings
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_settings (
	id INT NOT NULL DEFAULT NEXTVAL ('policies.sq_002_settings'),
	key VARCHAR(45) NOT NULL,
	value VARCHAR(200) NOT NULL,
	type VARCHAR(45) NOT NULL,
	version VARCHAR(45) NOT NULL,
	creation TIMESTAMP NOT NULL,
	creator INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_t_002_settings_t_002_users FOREIGN KEY (creator) REFERENCES policies.t_002_users (id),
	CONSTRAINT uq_t_002_settings_key UNIQUE (key))

    -- -----------------------------------------------------
    -- Indexes t_002_settings
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_settings_creator ON policies.t_002_settings (creator)

;

-- -----------------------------------------------------
-- Event Managment schema
-- -----------------------------------------------------
CREATE SCHEMA event_managment

    -- -----------------------------------------------------
    -- Sequence sq_002_categories
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_categories
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_categories
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_categories (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_categories'),
	name VARCHAR(200) NOT NULL,
	base_priority INT NULL,
	parent INT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_t_002_categories_t_002_categories FOREIGN KEY (parent) REFERENCES event_managment.t_002_categories (id),
	CONSTRAINT uq_t_002_categories_name UNIQUE (name),
	CONSTRAINT ck_t_002_categories_prority CHECK (base_priority > -1 and base_priority < 1001))

    -- -----------------------------------------------------
    -- Indexes t_002_categories
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_categories_parent ON event_managment.t_002_categories (parent)

    -- -----------------------------------------------------
    -- Sequence sq_002_events
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_events
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_events
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_events (
	id BIGINT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_events'),
	title VARCHAR(45) NOT NULL,
	description VARCHAR(200) NOT NULL,
  	creation TIMESTAMP NOT NULL,
  	category INT NOT NULL,
  	PRIMARY KEY (id),
  	CONSTRAINT fk_t_002_events_t_002_categories FOREIGN KEY (category) REFERENCES event_managment.t_002_categories (id))

    -- -----------------------------------------------------
    -- Indexes t_002_categories
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_events_category ON event_managment.t_002_events (category)

    -- -----------------------------------------------------
    -- Sequence sq_002_state_types
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_state_types 
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_state_types
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_state_types (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_state_types'),
	name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT uq_t_002_state_types_name UNIQUE (name))

    -- -----------------------------------------------------
    -- Sequence sq_002_states
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_states
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_states
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_states (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_states'),
	name VARCHAR(45) NOT NULL,
	state_type BIGINT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_t_002_states_t_002_state_types FOREIGN KEY (state_type) REFERENCES event_managment.t_002_state_types (id),
	CONSTRAINT uq_t_002_states_name UNIQUE (name))

    -- -----------------------------------------------------
    -- Sequence sq_002_events_track
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_events_track
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_events_track La prioridad debe estar con default a base_priority
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_events_track (
	id BIGINT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_events_track'),
	event BIGINT NOT NULL,
	state INT NOT NULL,
	manager INT NOT NULL,
    priority INT NOT NULL,
	start_time TIMESTAMP NOT NULL,
	end_time TIMESTAMP NULL,
	PRIMARY KEY (event, state, manager, id),
	CONSTRAINT fk_t_002_events_track_t_002_events FOREIGN KEY (event) REFERENCES event_managment.t_002_events (id),
	CONSTRAINT fk_t_002_events_track_t_002_states FOREIGN KEY (state) REFERENCES event_managment.t_002_states (id),
	CONSTRAINT fk_t_002_events_track_t_002_users  FOREIGN KEY (manager) REFERENCES policies.t_002_users (id))

    -- -----------------------------------------------------
    -- Indexes t_002_events_track
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_events_track_t_002_events ON event_managment.t_002_events_track (event)
    CREATE INDEX idx_t_002_events_track_t_002_states ON event_managment.t_002_events_track (state)
    CREATE INDEX idx_t_002_events_track_t_002_users ON event_managment.t_002_events_track (manager)

    -- -----------------------------------------------------
    -- Sequence sq_002_events_remarks
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_events_remarks
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_events_remarks
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS event_managment.t_002_events_remarks (
	id BIGINT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_events_remarks'),
	author INT NOT NULL,
	event BIGINT NOT NULL,
	state INT NOT NULL,
	manager INT NOT NULL,
	track BIGINT NOT NULL,
	content VARCHAR(200) NOT NULL,
	creation TIMESTAMP NOT NULL,
	PRIMARY KEY (id, author, event, state, manager, track),
	CONSTRAINT fk_t_002_events_remarks_t_002_users FOREIGN KEY (author) REFERENCES policies.t_002_users (id),
	CONSTRAINT fk_t_002_events_remarks_t_002_events_track FOREIGN KEY (event , state , manager , track) REFERENCES event_managment.t_002_events_track (event , state , manager , id))

    -- -----------------------------------------------------
    -- Indexes t_002_events_remarks
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_events_remarks_author ON event_managment.t_002_events_remarks (author)
    CREATE INDEX idx_t_002_events_remarks_track ON event_managment.t_002_events_remarks (event, state, manager, track)

    -- -----------------------------------------------------
    -- Sequence sq_002_users_track
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_users_track
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table black_box.it002_users_track
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_users_track (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_users_track'),
	state INT NOT NULL,
	owner INT NOT NULL,
	start_time TIMESTAMP NOT NULL,
	end_time TIMESTAMP NULL,
	PRIMARY KEY (id, state, owner),
	CONSTRAINT fk_t_002_users_track_t_002_states FOREIGN KEY (state) REFERENCES event_managment.t_002_states (id),
	CONSTRAINT fk_t_002_users_track_t_002_users FOREIGN KEY (owner) REFERENCES policies.t_002_users (id))

    -- -----------------------------------------------------
    -- Indexes t_002_users_track
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_users_track_state ON event_managment.t_002_users_track (state)
    CREATE INDEX idx_t_002_users_track_user ON event_managment.t_002_users_track (owner)

    -- -----------------------------------------------------
    -- Sequence sq_002_step_types
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_step_types
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_step_types
    -- -----------------------------------------------------
    CREATE TABLE t_002_step_types (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_step_types'),
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id))

    -- -----------------------------------------------------
    -- Sequence sq_002_steps
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_steps
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_steps
    -- -----------------------------------------------------
    CREATE TABLE t_002_steps (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_steps'),
	description VARCHAR(200) NOT NULL,
        step_type INT NOT NULL,
	PRIMARY KEY (id),
        CONSTRAINT fk_t_002_steps_t_002_step_types FOREIGN KEY (step_type) REFERENCES event_managment.t_002_step_types (id))

    -- -----------------------------------------------------
    -- Sequence sq_002_protocols
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_protocols
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table black_box.it002_protocols
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_protocols (
	id INT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_protocols'),
	category INT NOT NULL,
	step INT NOT NULL,
	step_order INT NOT NULL,
	PRIMARY KEY (id, category, step),
	CONSTRAINT fk_t_002_protocols_t_002_categories FOREIGN KEY (category) REFERENCES event_managment.t_002_categories (id),
	CONSTRAINT fk_t_002_protocols_t_002_steps FOREIGN KEY (step) REFERENCES event_managment.t_002_steps (id))

    CREATE INDEX idx_t_002_protocols_category ON event_managment.t_002_protocols (category)
    CREATE INDEX idx_t_002_protocols_step ON event_managment.t_002_protocols (step)
    
    -- -----------------------------------------------------
    -- Table t_002_flow_rules
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_flow_rules (
	current INT NOT NULL,
	next INT NOT NULL,
	PRIMARY KEY (current, next),
	CONSTRAINT fk_t_002_rules_t_002_states FOREIGN KEY (current) REFERENCES event_managment.t_002_states (id),
	CONSTRAINT fk_t_002_rules_t_002_states_2 FOREIGN KEY (next) REFERENCES event_managment.t_002_states (id))

    -- -----------------------------------------------------
    -- Indexes t_002_flow_rules
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_rules_next ON event_managment.t_002_flow_rules (next)

    -- -----------------------------------------------------
    -- Sequence sq_002_users_remarks
    -- -----------------------------------------------------
    CREATE SEQUENCE sq_002_users_remarks
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	NO CYCLE

    -- -----------------------------------------------------
    -- Table t_002_users_remarks
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS t_002_users_remarks (
	id BIGINT NOT NULL DEFAULT NEXTVAL ('event_managment.sq_002_users_remarks'),
	track INT NOT NULL,
	state INT NOT NULL,
	owner INT NOT NULL,
	content VARCHAR(200) NOT NULL,
	creation TIMESTAMP NOT NULL,
	PRIMARY KEY (id, track, state, owner),
	CONSTRAINT fk_t_002_users_remarks_t_002_users_track FOREIGN KEY (track , state , owner) REFERENCES event_managment.t_002_users_track (id , state , owner))

    -- -----------------------------------------------------
    -- Indexes t_002_users_remarks
    -- -----------------------------------------------------
    CREATE INDEX idx_t_002_users_remarks_track ON event_managment.t_002_users_remarks (track, state, owner)

;
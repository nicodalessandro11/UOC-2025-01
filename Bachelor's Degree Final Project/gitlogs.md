# Git logs tracking

```bash
commit 2975f2d1e52eeeccae69e97f9a2d86e2203eab4c (HEAD -> main, origin/main, origin/HEAD)
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 22:51:56 2025 +0000

    🗃️ DB | 2025-04-16 | Added PostGIS schema fixes and point features loader

    - Updated  to fix SECURITY DEFINER view issue and apply correct RLS policies
    - Fixed  to create  without the SECURITY DEFINER flag
    - Added new script  under  to handle ETL of cultural equipment
    - Adjusted  and  to support the new point features pipeline
    - Updated  with initial values for  (libraries, museums, etc.)

    Linter errors on Supabase dashboard are resolved and ETL for Barcelona point features is now operational.

commit 42b05696c9e219634688d174401466a0ee4ce161
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:26:41 2025 +0000

    🗃️ DB | 2025-04-15 | Added city_id to geographical_unit_view for clarity

    - Updated database/views.sql to include `city_id` in all levels of geographical_unit_view
    - Enables disambiguation of neighbourhood and district codes across different cities
    - Facilitates clearer joins and lookups in future ETLs (e.g. point_features, indicators)

    This change improves data traceability across geo levels and supports multi-city datasets more reliably.

commit a1049fefe90311c00a84dfc5e4a05358b7de05f3
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:18:19 2025 +0000
:

commit 2975f2d1e52eeeccae69e97f9a2d86e2203eab4c (HEAD -> main, origin/main, origin/HEAD)
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 22:51:56 2025 +0000

    🗃️ DB | 2025-04-16 | Added PostGIS schema fixes and point features loader

    - Updated  to fix SECURITY DEFINER view issue and apply correct RLS policies
    - Fixed  to create  without the SECURITY DEFINER flag
    - Added new script  under  to handle ETL of cultural equipment
    - Adjusted  and  to support the new point features pipeline
    - Updated  with initial values for  (libraries, museums, etc.)

    Linter errors on Supabase dashboard are resolved and ETL for Barcelona point features is now operational.

commit 42b05696c9e219634688d174401466a0ee4ce161
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:26:41 2025 +0000

    🗃️ DB | 2025-04-15 | Added city_id to geographical_unit_view for clarity

    - Updated database/views.sql to include `city_id` in all levels of geographical_unit_view
    - Enables disambiguation of neighbourhood and district codes across different cities
    - Facilitates clearer joins and lookups in future ETLs (e.g. point_features, indicators)

    This change improves data traceability across geo levels and supports multi-city datasets more reliably.

commit a1049fefe90311c00a84dfc5e4a05358b7de05f3
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
:

commit 2975f2d1e52eeeccae69e97f9a2d86e2203eab4c (HEAD -> main, origin/main, origin/HEAD)
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 22:51:56 2025 +0000

    🗃️ DB | 2025-04-16 | Added PostGIS schema fixes and point features loader

    - Updated  to fix SECURITY DEFINER view issue and apply correct RLS policies
    - Fixed  to create  without the SECURITY DEFINER flag
    - Added new script  under  to handle ETL of cultural equipment
    - Adjusted  and  to support the new point features pipeline
    - Updated  with initial values for  (libraries, museums, etc.)

    Linter errors on Supabase dashboard are resolved and ETL for Barcelona point features is now operational.

commit 42b05696c9e219634688d174401466a0ee4ce161
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:26:41 2025 +0000

    🗃️ DB | 2025-04-15 | Added city_id to geographical_unit_view for clarity

    - Updated database/views.sql to include `city_id` in all levels of geographical_unit_view
    - Enables disambiguation of neighbourhood and district codes across different cities
    - Facilitates clearer joins and lookups in future ETLs (e.g. point_features, indicators)

    This change improves data traceability across geo levels and supports multi-city datasets more reliably.

commit a1049fefe90311c00a84dfc5e4a05358b7de05f3
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:18:19 2025 +0000
:...skipping...
commit 2975f2d1e52eeeccae69e97f9a2d86e2203eab4c (HEAD -> main, origin/main, origin/HEAD)
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 22:51:56 2025 +0000

    🗃️ DB | 2025-04-16 | Added PostGIS schema fixes and point features loader

    - Updated  to fix SECURITY DEFINER view issue and apply correct RLS policies
    - Fixed  to create  without the SECURITY DEFINER flag
    - Added new script  under  to handle ETL of cultural equipment
    - Adjusted  and  to support the new point features pipeline
    - Updated  with initial values for  (libraries, museums, etc.)

    Linter errors on Supabase dashboard are resolved and ETL for Barcelona point features is now operational.

commit 42b05696c9e219634688d174401466a0ee4ce161
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:26:41 2025 +0000

    🗃️ DB | 2025-04-15 | Added city_id to geographical_unit_view for clarity

    - Updated database/views.sql to include `city_id` in all levels of geographical_unit_view
    - Enables disambiguation of neighbourhood and district codes across different cities
    - Facilitates clearer joins and lookups in future ETLs (e.g. point_features, indicators)

    This change improves data traceability across geo levels and supports multi-city datasets more reliably.

commit a1049fefe90311c00a84dfc5e4a05358b7de05f3
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 21:18:19 2025 +0000

    🗃️ DB | 2025-04-15 | Normalize geo code fields and fix geographical view

    - Updated all ETL scripts to store  and  as integers
    - Aligned database schema to define  and  as INTEGER types
    - Fixed  to cast all  fields as INTEGER to enable type-safe joins
    - Ensures compatibility for future joins in ETLs for indicators and point_features using (geo_level_id, code)

    This change standardizes geo code fields across the DB and ETL to prevent type mismatch errors.

commit 15e2c2ea25ea169b7d79a0a86c1ed3184bef26b7
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 18:46:15 2025 +0000

    ♻️ Refactor | 2025-04-15 | Cleaned up compiled .pyc files from venv

    - Deleted unnecessary cached Python files () under  for a lighter repo
    - Prevented tracking of virtual environment generated files by Git
    - Reflects automated clean-up process often run via 🧼 Cleaning processed files and cache...
    rm -rf data/processed/*
    find . -type d -name "__pycache__" -exec rm -rf {} +
    rm -rf .pytest_cache
    ✅ Clean complete. or pre-deploy steps

    This commit helps maintain a clean working directory by avoiding committed environment artifacts.

commit 4796ad94d7fda57f2059b30494f8bd4bb6dc51bc
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 17:59:25 2025 +0000

    🛠️ Setup | 2025-04-15 | Finalized Makefile automation and documentation cleanup

    - Replaced old  with standardized  for full project automation
    - Added modular targets for , , , ,  and
    - Created  with detailed environment and installation instructions
    - Deleted outdated documents: ,
    - Updated  to separate ETL and upload logic and improve flow control
    - Extended  to include full commit log and project evolution

    This commit wraps up the day’s refactor by enabling end-to-end reproducibility and clearer documentation for project onboarding.
    !

commit c953ef8b1e3849caed679857fc7a47db3b4b2086
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 17:27:28 2025 +0000

    🧪 Test | 2025-04-15 | Added geometry integrity tests and silenced Shapely deprecation warning

    - Updated test_geometry_integrity.py to ensure geometry consistency between raw and processed files using GeoPandas and Shapely
    - Added  logic for correct module resolution in pytest
    - Created pytest.ini to configure test discovery and suppress shapely.geos deprecation warnings

    Ensures reliable regression checks on ETL geometry outputs and prepares test suite for CI integration.

commit 76f3de41e43f8900865d8124958d9d27720904f5
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 17:08:51 2025 +0000

    🗃️ DB | 2025-04-15 | Updated processed geojson exports and refined schema

    - Regenerated insert_ready_*.json files for districts and neighbourhoods (Barcelona & Madrid) with correct district_id mappings
    - Ensured consistency with new dynamic Supabase ID resolution during ETL
    - Minor refinements to database/schema.sql to align with current pipeline structure

    These changes finalize the corrected ETL output and schema alignment after refactor.

commit a3b4705e3acf729ff622209b5ae2b4c0b14fea87
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 17:07:10 2025 +0000

    ♻️ Refactor | 2025-04-15 | Separated ETL and upload stages for districts and neighbourhoods

    - Refactored ingest_data.py to ensure districts are loaded and uploaded before neighbourhood ETL begins
    - Updated upload_to_supabase.py to split upload functions into run_district_upload and run_neighbourhood_upload
    - Replaced hardcoded district_id mappings in Madrid and Barcelona neighbourhood scripts with dynamic lookup from Supabase
    - Improved script reliability by removing assumptions on auto-increment IDs and ensuring valid foreign key relationships

    This change ensures consistent ETL pipeline execution and eliminates dependency errors during neighbourhood uploads.

commit e5ffff8dd1fad4bdea78b9ebdecf937a8899b5aa
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 14:19:27 2025 +0000

    🛠️ Setup | 2025-04-15 | Refactored Makefile with modular test commands

    - Added separate targets for test_processed and test_geometry under test suite
    - Clarified comments for each command including etl, clean, upload, and seed
    - Enables isolated test runs and better developer experience during debugging

    This update improves maintainability and transparency in running parts of the pipeline independently.

commit 2b643471edcc2d3ff9e3585bfe5f176ca87537ce
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 14:17:36 2025 +0000

    📦 Feature | 2025-04-15 | Added ETL scripts and processed data for BCN & Madrid

    - Created `load_districts.py` and `load_neighbourhoods.py` for both Barcelona and Madrid under `data/scripts/`
    - Generated `insert_ready_*.json` files with cleaned and WKT-wrapped geometries in `data/processed/`
    - Ensured all districts and neighbourhoods are standardized and mapped to city IDs
    - Added seed.sql to initialize PostGIS schema with all required tables and constraints
    - Added `test_geometry_integrity.py` to validate WKT consistency between raw and processed files
    - Includes Makefile commands (`etl`, `test`) for pipeline reproducibility

    Enables complete ETL and test pipeline for loading and validating geospatial data per city.

commit 1597e31caddcb6096a9f1125693f9d31585e7d10
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 13:12:00 2025 +0000

    📄 Docs | 2025-04-15 | Added commit message template for project documentation

commit a57eae2c3aa90279305dcda1aba3faed8c28d0e8
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 13:07:33 2025 +0000

    🗃️ DB | 2025-04-15 | Added full PostGIS schema and unified views

    - Added database/schema.sql with table definitions for cities, districts, neighbourhoods, indicators, and point_features
    - Added database/views.sql including 'geographical_unit_view' to unify all geo levels
    - Enables reproducibility and setup of Supabase schema from scratch

commit 323740db08340decf5d9deb4fc390aa1e042fda5
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 12:58:47 2025 +0000

    📦 Data Pipeline | 2025-04-15 | ETL pipeline + Supabase config for geographical data

    - Added working .env and .env.example with Supabase URL and service key
    - Removed supabase-password.txt and old placeholder files
    - Created ETL scripts for Barcelona and Madrid (districts + neighbourhoods)
    - Prepared upload script using supabase-py to send data from JSON to Supabase
    - Updated ingest_data.py and requirements.txt to support ETL workflow
    - Added real data sources: GeoJSON/TopoJSON files for BCN and MAD

    This commit sets up a complete ETL-to-database pipeline for geospatial data ingestion.

commit 081833a06940e5b3e38b7bbf1c4f797fdce3309d
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 12:56:18 2025 +0000

    🔐 Config | 2025-04-15 | Added Supabase credentials to .env.example and removed exposed password

    - Updated .env.example with SUPABASE_URL and SUPABASE_SERVICE_KEY placeholders
    - Ensured local .env includes real credentials (not committed)
    - Deleted supabase-password.txt to prevent accidental exposure

commit f3098517bc7788a67b1eda1b7b5dba8cb818ad93
Author: Nico D'Alessandro <nico.dalessandro@playngo.com>
Date:   Tue Apr 15 13:55:41 2025 +0200

    🔐 Config | 2025-04-15 | Added safe .env.example and removed password file

    - Added .env.example with Supabase placeholders
    - Updated .gitignore to exclude .env from versioning
    - Removed supabase-password.txt to avoid exposed credentials

commit 7a6a43530e67ec643d31c524f18579c828dbd731
Author: Nicolas D'Alessandro <48628102+nicodalessandro11@users.noreply.github.com>
Date:   Tue Apr 15 11:40:47 2025 +0000

    🛠️ Setup | 2025-04-14 | Initial backend + Supabase integration setup

    - Deleted placeholder .env.example (replaced with working .env file locally)
    - Modified backend entrypoint and database logic (main.py, db.py)
    - Updated backend/frontend Dockerfiles to align with Supabase config
    - Adjusted docker-compose to reflect real env variables
    - Created documentation stub for methods (docs/methods_resources.md)
    - Added supabase-password.txt (⚠️ this should be ignored or encrypted later)
    - Prepared requirements.txt for deployment

commit 148408669fc953b44be9acc04689003d3aca2b2c
Author: Nico D'Alessandro <nico.dalessandro@playngo.com>
Date:   Mon Apr 14 20:38:20 2025 +0200

    Initial project structure from CA3 template
```

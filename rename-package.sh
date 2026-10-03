#!/bin/bash
# Rename the Messaging app package: com.android.messaging -> com.tiraq.messaging
# Run from the repo root of android_packages_apps_Messaging:
#     bash rename-package.sh
set -e

SELF=rename-package.sh

mkdir -p src/com/tiraq
git mv src/com/android/messaging src/com/tiraq/messaging

# rewrite every text file that references the old package (skips .git, binaries, this script)
grep -rlI 'com\.android\.messaging' . | grep -v '/\.git/' | grep -v "$SELF" | while read -r f; do
  sed -i 's/com\.android\.messaging/com.tiraq.messaging/g' "$f"
done

git mv com.android.messaging.allowlist.xml com.tiraq.messaging.allowlist.xml
rmdir src/com/android 2>/dev/null || true

git add -A
git commit -m "Messaging: rename package to com.tiraq.messaging"

echo
echo "Remaining old-package references (should be none):"
grep -rlI 'com\.android\.messaging' . | grep -v '/\.git/' | grep -v "$SELF" || echo "  (none)"

# Messaging

A **Material 3 (Material You)** restyle of the LineageOS / Axion Messaging app,
repackaged as `com.tiraq.messaging` so it installs alongside any ROM's built-in
Messaging app instead of replacing it.

## Highlights

- **Material 3 / Material You** — `Theme.Material3.DayNight` with the M3 colour roles
  mapped onto the platform dynamic palette. Light and dark both follow the system.
- **Own package** — installs as `com.tiraq.messaging`, with its own provider
  authorities, so it never collides with the ROM's Messaging.
- **lineage-23.2 icon** — the blue "…" speech bubble, including the separate
  monochrome layer used by themed icons.

## What changed

- **Conversation list** — flat top bar, *All / Personal / OTP / Offers* filter chips
  with real filtering, rounded card rows and an unread dot.
- **Chat** — Material 3 Expressive compose bar (pill field with the emoji and camera
  buttons inside, circular attach and send buttons) plus a built-in emoji picker.
- **Start chat** — pill search field, segmented pill tabs, card rows with a "Mobile"
  chip and a call button.
- **Settings** — card-style rows with circular icons and accent section headers.
- **Conversation** — flat dark top bar instead of the conversation accent blue.

## Credits

A restyle, not a rewrite — most of the code is other people's work:

- **The Android Open Source Project** — the original Messaging app.
- **The LineageOS Project** — ongoing maintenance of the app.
- **The AxionAOSP Project** — the icon and packaging this builds on.

Per-file copyright headers are preserved. The Material 3 restyle and the
`com.tiraq.messaging` packaging are the new work.

## License

Apache License, Version 2.0.

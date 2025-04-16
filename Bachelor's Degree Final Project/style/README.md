# UI/UX Style Guide – “Are You Query-ous?”

This document defines the visual identity and style guidelines to ensure consistency in design and implementation across the project.

---

## 1. Brand Identity

The brand evokes a modern, friendly, and tech-oriented personality. It features clean geometric visuals, bold contrast, and approachable typography, designed to appeal to a developer-savvy audience.

---

## 2. Color Palette

| Name          | Hex Code  | Usage                                            |
| ------------- | --------- | ------------------------------------------------ |
| Primary Green | `#4CAF50` | Main branding color. Used in vibrant themes.     |
| Primary Blue  | `#1468B1` | Secondary tone for dark or technical themes.     |
| Primary Brown | `#A36322` | Warm tone for contrast and variation.            |
| White         | `#FFFFFF` | Text on dark backgrounds, cards, empty states.   |
| Light Grey    | `#F2F2F2` | Neutral backgrounds, dividers, UI surfaces.      |
| Dark Grey     | `#333333` | Body text, secondary icons, neutral UI elements. |
| Accent Yellow | `#FFD33D` | CTA buttons, alerts, highlights.                 |
| Accent Teal   | `#00BFA5` | Success, confirmations, interactive highlights.  |

> **Accessibility Tip:** All text-over-background color combinations meet at least WCAG AA contrast ratio.

---

## 3. Typography

- **Primary Font:** `Inter`  
  _(Fallbacks: Helvetica Neue, Arial, sans-serif)_

| Use Case        | Size | Weight | Color   |
| --------------- | ---- | ------ | ------- |
| Main Title (H1) | 32px | 700    | #333333 |
| Subtitle (H2)   | 24px | 600    | #333333 |
| Section Title   | 20px | 600    | #4CAF50 |
| Body Text       | 16px | 400    | #333333 |
| Caption/Note    | 14px | 400    | #666666 |
| Button Text     | 16px | 600    | #FFFFFF |

---

## 4. Buttons

| Type       | Background | Text Color | Border    | Hover Effect           |
| ---------- | ---------- | ---------- | --------- | ---------------------- |
| Primary    | #1468B1    | #FFFFFF    | None      | Slight darken on hover |
| Secondary  | #FFFFFF    | #1468B1    | 1px solid | Light blue background  |
| Disabled   | #F2F2F2    | #AAAAAA    | None      | No interaction         |
| CTA/Accent | #FFD33D    | #333333    | None      | Drop shadow, scale up  |

- **Border Radius:** `6px`
- **Padding:** `12px 24px`

---

## 5. Layout System

- **Max Container Width:** `1200px`
- **Grid System:** 12 columns with `24px` gutter
- **Spacing Scale:** based on multiples of 8

```text
XS → 4px
S → 8px
M → 16px
L → 24px
XL → 32px
XXL → 64px
```

---

## 6. Iconography & Shape Language

- Style: Flat, minimal, monochrome
- Standard Size: `24px`
- Stroke Weight: `1.5px`
- Key icons: database, brackets `< >`, agent bot, code tag, question mark

---

## 7. Branding Mascot

- **Character:** A friendly database figure with eyes and angled brackets as arms.
- **Use Cases:** Hero sections, landing pages, presentation visuals, swag.
- **Do Not:** Alter the mascot’s proportions, face, or position.

---

## 8. Accessibility & Dark Mode

- **Contrast Ratio (minimum):** 4.5:1 for body text
- **Focus States:** Must be visually clear and keyboard navigable
- **Alt Tags:** Required for all icons and illustrations
- **Dark Mode:** Supported using:
  - Background: `#121212`
  - Text: `#F2F2F2`
  - Accent: Adjust to retain contrast

---

## 9. Included Files

- `mascot-green.png`, `mascot-blue.png`, `mascot-brown.png` – Mascot variants
- `colors-typography.png` – Palette with all primary and accent colors and Typography scale.
- `buttons.png` – Examples of button styles and states
- `icons/` – Folder with example of key icons.

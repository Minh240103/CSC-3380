// LSU Scheduler — main.js

document.addEventListener('DOMContentLoaded', () => {

    // ─── Filter pills on dashboard ───────────────────────────────────
    const pills = document.querySelectorAll('.filter-pill');
    const courseRows = document.querySelectorAll('.course-row, .course-row--ineligible');

    pills.forEach(pill => {
        pill.addEventListener('click', () => {
            pills.forEach(p => p.classList.remove('active'));
            pill.classList.add('active');

            const filter = pill.dataset.filter;

            courseRows.forEach(row => {
                if (filter === 'all') {
                    row.style.display = '';
                    return;
                }
                if (filter === 'required') {
                    const hasRequired = row.querySelector('.tag--required');
                    row.style.display = hasRequired ? '' : 'none';
                    return;
                }
                if (filter === 'elective') {
                    const type = row.dataset.type;
                    row.style.display = (type === 'ELECTIVE') ? '' : 'none';
                    return;
                }
                if (filter === 'fall-only') {
                    const semTag = row.querySelector('.tag--semester');
                    row.style.display = (semTag && semTag.textContent.includes('Fall')) ? '' : 'none';
                    return;
                }
                if (filter === 'no-conflict') {
                    const hasConflict = row.querySelector('.conflict-warn');
                    row.style.display = hasConflict ? 'none' : '';
                    return;
                }
            });
        });
    });

    // ─── Auto-dismiss flash messages after 4 seconds ─────────────────
    const flashes = document.querySelectorAll('.flash');
    flashes.forEach(flash => {
        setTimeout(() => {
            flash.style.transition = 'opacity 0.4s';
            flash.style.opacity = '0';
            setTimeout(() => flash.remove(), 400);
        }, 4000);
    });

    // ─── Confirm before removing a section ───────────────────────────
    document.querySelectorAll('.btn-remove').forEach(btn => {
        btn.addEventListener('click', e => {
            if (!confirm('Remove this course from your schedule?')) {
                e.preventDefault();
            }
        });
    });

    // ─── Highlight active nav item from URL ──────────────────────────
    const path = window.location.pathname;
    document.querySelectorAll('.nav-item').forEach(item => {
        const href = item.getAttribute('href');
        if (href && path.startsWith(href) && href !== '/') {
            item.classList.add('active');
        }
    });

});

import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-tableau-de-bord-administration',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './tableau-de-bord.component.html',
    styleUrls: ['./tableau-de-bord.component.css']
})
export class TableauDeBordComponent {
    isSidebarOpen = true;

    toggleSidebar() {
        this.isSidebarOpen = !this.isSidebarOpen;
    }
}

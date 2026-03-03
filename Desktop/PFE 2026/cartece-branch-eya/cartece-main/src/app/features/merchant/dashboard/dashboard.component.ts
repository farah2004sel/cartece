import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Dashboard } from 'src/app/core/models/dashboard.model';
import { DashboardService } from 'src/app/core/services/dashboard.service';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  dashboard$!: Observable<Dashboard>;
  profileData$!: Observable<any>;

  constructor(
    private dashboardService: DashboardService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.dashboard$ = this.dashboardService.getDashboard();
    this.profileData$ = this.authService.profileData$;
  }

}
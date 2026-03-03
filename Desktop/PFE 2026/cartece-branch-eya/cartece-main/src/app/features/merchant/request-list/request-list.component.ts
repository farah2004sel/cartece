import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/core/models/request.model';
import { RequestService } from 'src/app/core/services/request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  requests: Request[] = [];

  constructor(private requestService: RequestService) {}

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.requestService.getAllRequests().subscribe(data => {
      this.requests = data;
    });
  }
}
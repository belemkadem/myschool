import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITutorType } from 'app/shared/model/tutor-type.model';

@Component({
  selector: 'jhi-tutor-type-detail',
  templateUrl: './tutor-type-detail.component.html',
})
export class TutorTypeDetailComponent implements OnInit {
  tutorType: ITutorType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tutorType }) => (this.tutorType = tutorType));
  }

  previousState(): void {
    window.history.back();
  }
}

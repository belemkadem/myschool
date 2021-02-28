import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGradeClass } from 'app/shared/model/grade-class.model';

@Component({
  selector: 'jhi-grade-class-detail',
  templateUrl: './grade-class-detail.component.html',
})
export class GradeClassDetailComponent implements OnInit {
  gradeClass: IGradeClass | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gradeClass }) => (this.gradeClass = gradeClass));
  }

  previousState(): void {
    window.history.back();
  }
}

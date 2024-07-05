package com.ikozikov.service.subdivision.dto;

import com.ikozikov.service.shared.utils.TroopsSide;
import com.ikozikov.service.subdivision.model.SubdivisionLabel;
import com.ikozikov.service.subdivision.model.SubdivisionModel;
import lombok.Data;

@Data
public class SubdivisionDto {
  private String name;
  private String description;
  private SubdivisionLabel label;
  private TroopsSide type;

  public SubdivisionModel toModel() {
    var subdivisionModel = new SubdivisionModel();
    subdivisionModel.setName(name);
    subdivisionModel.setDescription(description);
    subdivisionModel.setLabel(label);
    subdivisionModel.setType(type);
    return subdivisionModel;
  }
}

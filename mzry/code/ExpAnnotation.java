/**
 * Minimal status id.
 */
@Exp(mapField = "statusId", type = Condition.ConditionType.GREATER_OR_EQUALS)
public Integer startId;

/**
 * Submit user id.
 */
@Exp(mapField = "userByUserId", type = Condition.ConditionType.EQUALS)
public Integer userId;
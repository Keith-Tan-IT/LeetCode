class Solution(object):
    def minimumCost(self, cost):
        """
        :type cost: List[int]
        :rtype: int
        """
        return sum(total for i, total in enumerate(sorted(cost)) if (len(cost) - i) % 3)
        
